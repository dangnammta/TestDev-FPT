/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo.serialization;

import firo.utils.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author hoaronal
 */
class InputStreamSerializer extends Serializer {

    @Override
    public boolean canProcess(Object element) {
        return element instanceof InputStream;
    }

    @Override
    public void process(OutputStream outputStream, Object element)
            throws IOException {
        IOUtils.copy((InputStream) element, outputStream);
    }

}
