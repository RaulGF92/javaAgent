package es.raulgf92.agent;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.jar.Manifest;

import javax.imageio.stream.FileImageInputStream;

import org.yaml.snakeyaml.Yaml;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.agent.builder.AgentBuilder.Default;
import net.bytebuddy.agent.builder.AgentBuilder.Identified.Narrowable;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType.Builder;

import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;


public class Agent {
	
    public static void premain(String agentArgs, Instrumentation inst) {
    	
    	AgentConfig config = loadAgentConfig(agentArgs);
        Default defaultAgent = new AgentBuilder.Default();
        if(config != null) {
        	List<String> listPackages = config.getPackages();
        	for (String targetPackage : listPackages) {
            	loadLogger(
            			defaultAgent.type(ElementMatchers.nameStartsWith(targetPackage))
            			).installOn(inst);
			}
        } else {
        	loadLogger(
        			defaultAgent.type(ElementMatchers.any())
        			).installOn(inst);
        }
    }

	private static AgentBuilder loadLogger(Narrowable type) {
		return type.transform(new AgentBuilder.Transformer() {
			@Override
			public Builder<?> transform(Builder<?> builder, TypeDescription typeDescription,
					ClassLoader classLoader, JavaModule module) {
				try {
                    return builder.method(ElementMatchers.any())
                            .intercept(MethodDelegation.to(new LoggerInterceptor(UUID.randomUUID()))
                            		.andThen(SuperMethodCall.INSTANCE));
				} catch (Exception e) {
					System.out.println(e);
				}
				
				return builder;
			}

        });
	}

	private static AgentConfig loadAgentConfig(String agentArgs) {
		AgentConfig response = null;
		
		if(agentArgs != null) {
			try {
				Yaml yaml = new Yaml();
				File file = new File(agentArgs);
				FileReader fileReader = new FileReader(file);
				response = yaml.loadAs(fileReader, AgentConfig.class);
			} catch (Exception e) {
				System.out.println("Error obteniendo la configuración basica del agente");
				e.printStackTrace();
			}	
		}
		
		return response;
	}
		
}
