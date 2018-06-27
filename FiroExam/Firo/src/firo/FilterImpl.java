/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firo;
import firo.utils.Wrapper;
/**
 *
 * @author hoaronal
 */
public abstract class FilterImpl implements Filter, Wrapper {

    static final String DEFAULT_ACCEPT_TYPE = "*/*";

    private String path;
    private String acceptType;
    private Filter delegate;

    static FilterImpl create(final String path, final Filter filter) {
        return create(path, DEFAULT_ACCEPT_TYPE, filter);
    }

    static FilterImpl create(final String path, String acceptType, final Filter filter) {
        if (acceptType == null) {
            acceptType = DEFAULT_ACCEPT_TYPE;
        }
        return new FilterImpl(path, acceptType, filter) {
            @Override
            public void handle(Request request, Response response) throws Exception {
                filter.handle(request, response);
            }
        };
    }

    protected FilterImpl(String path, String acceptType) {
        this.path = path;
        this.acceptType = acceptType;
    }

    protected FilterImpl(String path, String acceptType, Filter filter) {
        this(path, acceptType);
        this.delegate = filter;
    }

    public abstract void handle(Request request, Response response) throws Exception;

    public String getAcceptType() {
        return acceptType;
    }

    String getPath() {
        return this.path;
    }

    @Override
    public Object delegate() {
        return this.delegate;
    }

}