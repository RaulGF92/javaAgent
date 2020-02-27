package es.raulgf92.application;

import java.util.Arrays;

public class Application {

	public static void main(String[] args) {

		Foo foo = new Foo();
		String msg = foo.doTask(Arrays.asList("Hola desde raúl"));
		System.out.println(msg);
		
		String msg2 = "Esto provocará un error";
		pruebaTraza(foo, msg2);
		
	}

	private static void pruebaTraza(Foo foo, String msg2) {
		try {
			//msg2 = foo.doTask2(msg2);
			String msg = foo.doTask(Arrays.asList("Hola desde raúl"));
			System.out.println(msg);
		} catch (Exception e) {
			System.out.println("Ha habido un error");
			e.printStackTrace();
		}
	}

}
