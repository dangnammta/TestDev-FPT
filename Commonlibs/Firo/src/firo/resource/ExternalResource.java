/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import firo.utils.StringUtils;

/**
 *
 * @author hoaronal
 */
public class ExternalResource extends AbstractFileResolvingResource {
	
    private final File file;

    public ExternalResource(String path) {
        file = new File(StringUtils.cleanPath(path));
    }

    public boolean isDirectory() {
        return file.isDirectory();
    }

    @Override
    public boolean exists() {
        return file.exists();
    }

    @Override
    public String getDescription() {
        return "external resource [" + file.getAbsolutePath() + "]";
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(file);
    }

    @Override
    public URL getURL() throws IOException {
        return file.toURI().toURL();
    }

    public String getPath() {
        return file.getPath();
    }

    @Override
    public String getFilename() {
        return StringUtils.getFilename(getPath());
    }

}
