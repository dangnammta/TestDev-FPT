/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo.serialization;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author hoaronal
 */
class DefaultSerializer extends Serializer {

    @Override
    public boolean canProcess(Object element) {
        return true;
    }

    @Override
    public void process(OutputStream outputStream, Object element) throws IOException {
        try {
            outputStream.write(element.toString().getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            throw new IOException(e);
        }
    }

}

