package com.adrianiglesia.atqr.model.response;

import com.adrianiglesia.atqr.model.User;

public class ResponseService {

        private User object;
        private String message;
        private Integer statusCode;

        public ResponseService(User object, String message, Integer status){
            this.object = object;
            this.message = message;
            this.statusCode = status;
        }

        public Object getObject() {
            return object;
        }

        public void setObject(User object) {
            this.object = object;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Integer getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(Integer statusCode) {
            this.statusCode = statusCode;
        }

}
