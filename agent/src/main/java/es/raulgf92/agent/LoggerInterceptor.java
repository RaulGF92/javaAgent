package es.raulgf92.agent;

import java.lang.reflect.Method;
import java.util.UUID;
import java.util.concurrent.Callable;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

public class LoggerInterceptor {
	
	private UUID randomUUID;

	public LoggerInterceptor(UUID randomUUID) {
		this.randomUUID = randomUUID;
	}

	@RuntimeType
	public Object log(@SuperCall Callable<Object> callable, @AllArguments Object[] allArguments, @Origin Method method, @Origin Class clazz) throws Exception {
		System.out.println(method.getName() + "Inicio Función");
		System.out.println(method.getName() + " UUID:" + this.randomUUID);
		
		try {
			Object obj = callable.call();
			System.out.println(method.getName() + "Respuesta: " + obj);
			return obj;
		} catch(Exception e) {
			System.out.println(method.getName() + "Errores: " + e);
			throw e;
		} finally {
			System.out.println(method.getName() + "Fin Función");
		}
	}
}