/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo.serialization;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author hoaronal
 */
public final class SerializerChain {

    private Serializer root;

    public SerializerChain() {

        DefaultSerializer defaultSerializer = new DefaultSerializer();

        InputStreamSerializer inputStreamSerializer = new InputStreamSerializer();
        inputStreamSerializer.setNext(defaultSerializer);

        BytesSerializer bytesSerializer = new BytesSerializer();
        bytesSerializer.setNext(inputStreamSerializer);

        this.root = bytesSerializer;
    }

    public void process(OutputStream outputStream, Object element) throws IOException {
        this.root.processElement(outputStream, element);
    }

}