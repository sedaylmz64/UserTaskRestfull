package com.example.deneme.controller.endpoint;

public class Endpoint {
    public static final String BASE_URI = "/";

    public class GetTasks {
        private GetTasks() {}

        public static final String URI = BASE_URI + "tasks";
    }

    public class GetTasksWithSecurity {
        private GetTasksWithSecurity() {}

        public static final String URI = BASE_URI + "secured/tasks";
    }
}
