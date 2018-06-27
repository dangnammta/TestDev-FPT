#load "runhelpers.cake"

using System.IO.Compression;
using System.Text.RegularExpressions;

/// <summary>
///  Generate the build identifier based on the RID and framework identifier.
///  Special rules when running on Travis (for publishing purposes).
/// </summary>
/// <param name="runtime">The RID</param>
/// <param name="framework">The framework identifier</param>
/// <returns>The designated build identifier</returns>
string GetBuildIdentifier(string runtime, string framework)
{
    var runtimeShort = "";
    // Default RID uses package name set in build script
    if (runtime.Equals("default"))
    {
        runtimeShort = Environment.GetEnvironmentVariable("SQLTOOLSSERVICE_PACKAGE_OSNAME");
    }
    else
    {
        // Remove version number. Note: because there are separate versions for Ubuntu 14 and 16,
        // we treat Ubuntu as a special case.
        if (runtime.StartsWith("ubuntu.14"))
        {
            runtimeShort = "ubuntu14-x64";
        }
        else if (runtime.StartsWith("ubuntu.16"))
        {
            runtimeShort = "ubuntu16-x64";
        }
        else
        {
            runtimeShort = Regex.Replace(runtime, "(\\d|\\.)*-", "-");
        }
    }   
    
    return $"{runtimeShort}-{framework}";
}

/// <summary>
///  Generate an archive out of the given published folder.
///  Use ZIP for Windows runtimes.
///  Use TAR.GZ for non-Windows runtimes.
///  Use 7z to generate TAR.GZ on Windows if available.
/// </summary>
/// <param name="runtime">The RID</param>
/// <param name="contentFolder">The folder containing the files to package</param>
/// <param name="archiveName">The target archive name (without extension)</param>
void DoArchive(string runtime, string contentFolder, string archiveName)
{
    // On all platforms use ZIP for Windows runtimes
    if (runtime.Contains("win") || (runtime.Equals("default") && IsRunningOnWindows()))
    {
        var zipFile = $"{archiveName}.zip";
        Zip(contentFolder, zipFile);
    }
    // On all platforms use TAR.GZ for Unix runtimes
    else
    {
        var tarFile = $"{archiveName}.tar.gz";
        // Use 7z to create TAR.GZ on Windows
        if (IsRunningOnWindows())
        {
            var tempFile = $"{archiveName}.tar";
            try
            {
                Run("7z", $"a \"{tempFile}\"", contentFolder)
                    .ExceptionOnError($"Tar-ing failed for {contentFolder} {archiveName}");
                Run("7z", $"a \"{tarFile}\" \"{tempFile}\"", contentFolder)
                    .ExceptionOnError($"Compression failed for {contentFolder} {archiveName}");
                System.IO.File.Delete(tempFile);
            }
            catch(Win32Exception)
            {
                Information("Warning: 7z not available on PATH to pack tar.gz results");
            }
        }
        // Use tar to create TAR.GZ on Unix
        else
        {
            Run("tar", $"czf \"{tarFile}\" .", contentFolder)
                .ExceptionOnError($"Compression failed for {contentFolder} {archiveName}");
        }
    }
}

/// <summary>
///  Copy EULAs into content folder prior to packaging archives.
/// </summary>
/// <param name="runtime">The RID</param>
/// <param name="framework">The framework identifier</param>
/// <param name="contentFolder">The folder containing the files to package</param>
/// <param name="packageFolder">The destination folder for the archive</param>
/// <param name="projectName">The project name</param>
/// <param name="workingDirectory">The working root directory name</param>
void CopyEulas(string runtime, string framework, string contentFolder, string packageFolder, string projectName, string workingDirectory)
{
    var files = System.IO.Directory.GetFiles(
        System.IO.Path.Combine(workingDirectory, "docs", "eulas"), 
        "*.RTF", 
        SearchOption.AllDirectories).ToList();

    foreach (var file in files) 
    {
       var dest = System.IO.Path.Combine(contentFolder, System.IO.Path.GetFileName(file));
       if (!System.IO.File.Exists(dest))
       {
           System.IO.File.Copy(file, dest);   
       }
    }
}

/// <summary>
///  Package a given output folder using a build identifier generated from the RID and framework identifier.
/// </summary>
/// <param name="runtime">The RID</param>
/// <param name="framework">The framework identifier</param>
/// <param name="contentFolder">The folder containing the files to package</param>
/// <param name="packageFolder">The destination folder for the archive</param>
/// <param name="projectName">The project name</param>
/// <param name="workingDirectory">The working root directory name</param>
void Package(string runtime, string framework, string contentFolder, string packageFolder, string projectName, string workingDirectory)
{
    // binplace EULA files prior to archiving
    CopyEulas(runtime, framework, contentFolder, packageFolder, projectName, workingDirectory);

    var buildIdentifier = GetBuildIdentifier(runtime, framework);
    if (buildIdentifier != null)
    {
        DoArchive(runtime, contentFolder, $"{packageFolder}/{projectName}-{buildIdentifier}");
    }
}
