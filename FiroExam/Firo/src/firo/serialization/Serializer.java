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
public abstract class Serializer {

    private Serializer next;

    public void setNext(Serializer serializer) {
        this.next = serializer;
    }

    public void processElement(OutputStream outputStream, Object element) throws IOException {
        if (canProcess(element)) {
            process(outputStream, element);
        } else {
            if (next != null) {
                this.next.processElement(outputStream, element);
            }
        }
    }

    public abstract boolean canProcess(Object element);

    public abstract void process(OutputStream outputStream, Object element) throws IOException;
}
