package es.raulgf92.application;

import java.util.List;

public class Foo {

	public String doTask(List<String> tasks) {
		return "Esta es una tarea";
	}
	
	
	public String doTask2(String task) throws Exception {
		if(true)
			throw new Exception("Por mis huevos");
		return task;
	}
}
