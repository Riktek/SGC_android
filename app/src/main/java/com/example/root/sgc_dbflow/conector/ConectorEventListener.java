package com.example.root.sgc_dbflow.conector;

import java.util.EventListener;

public interface ConectorEventListener<T> extends EventListener {

    void OnSuccess(Response response);
    void OnFailure(Response response);

}
