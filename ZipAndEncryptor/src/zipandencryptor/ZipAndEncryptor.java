/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zipandencryptor;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anbq
 */
public class ZipAndEncryptor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String output_zip_file = "testzipfolder.zip";
        String folder_path = "/home/anbq/Downloads/testzipfolder";
        ZipUtils appZip = new ZipUtils(output_zip_file, folder_path);
        appZip.generateFileList(new File(folder_path));
        appZip.zipIt(output_zip_file);
    }
    
}
