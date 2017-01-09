package com.example.atm.entities;

import java.util.List;

/**
 * Created by Tao.ZT.Zhang on 2016/9/30.
 */

public class Message {
    /**
     * Message : The request is invalid.
     * ModelState : {"Info":["No Data Found"]}
     */

    private String Message;
    private ModelStateBean ModelState;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public ModelStateBean getModelState() {
        return ModelState;
    }

    public void setModelState(ModelStateBean ModelState) {
        this.ModelState = ModelState;
    }

    public static class ModelStateBean {
        private List<String> Info;

        public List<String> getInfo() {
            return Info;
        }

        public void setInfo(List<String> Info) {
            this.Info = Info;
        }
    }
}
