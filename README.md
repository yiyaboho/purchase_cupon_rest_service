# Challenge: Cupon de compra
# Objetivo:

El objetivo es permitir mediante un API, ejecutar la siguiente funcionalidad:

*1* Entregar una lista de items dados unos id y un monto, las lista final no debe superar el monto dado

*2* Entregar el top de items favoritos

*3* Desplegar esa API en un cloud computing libre

**Consideraciones**
- Crear lo necesario para acumular los favoritos y luego poder hacer la consulta del top 5
- Esta api tendría que escalar para soportar tráfico de hasta 100K rpm

## Detalles de la implementacion

Tecnologias

SpringBoot 2.7 
Java 11
RDS MySQL

**Diagrama de Contexto**

En el diagrama se muestra de manera general las relaciones e interacciones del API con otros sistemas.

![Diagrama de Contexto](https://user-images.githubusercontent.com/106846429/173261574-0a45695c-afb9-4659-a84a-c30d7abeee5c.jpg) 


**Diagrama de Clases**

![Diagrama de clases](https://user-images.githubusercontent.com/106846429/173464940-5fa92626-2225-4ba8-a256-2cadc7a127c8.jpg)


**Diagrama de despliegue**

![Cupon de compra-deploy](https://user-images.githubusercontent.com/106846429/173350057-6121d83f-d7f4-435d-ad73-f16be07c0741.jpg)


## Instrucciones

- Clonar el repo, utilizar la rama main

- Generar artefacto

	mvn package
	
- Ejecutar la aplicacion: en la carpeta target
	
	java -jar purchase_coupon_rest_service-0.0.1.jar



**TEST Nivel 1:**

	curl --location --request POST 'http://springbootapp-env.eba-vmxfgd5a.us-east-1.elasticbeanstalk.com/coupon' \
	--header 'Content-Type: application/json' \
	--data-raw '{
	"item_ids": ["MLA1112351518", "MLA1125397650", "MLA580013811", "MLA757113588", "MLA916058884"],
	"amount": 6000
	}'

**TETS Nivel 2:**

	curl --location --request GET 'http://springbootapp-env.eba-vmxfgd5a.us-east-1.elasticbeanstalk.com/coupon/stats' \
	--header 'Content-Type: application/json'


**TEST Nivel 3**


Se publico en AWS, la url es:

	http://springbootapp-env.eba-vmxfgd5a.us-east-1.elasticbeanstalk.com



## Funcionalidad adicional

**Agregar favoritos:**

	curl --location --request POST 'http://springbootapp-env.eba-vmxfgd5a.us-east-1.elasticbeanstalk.com/coupon/favorite/user/tatelite/item/MLA19101769' \
	--header 'Content-Type: application/json'
	
**Eliminar favoritos**

	curl --location --request DELETE 'http://springbootapp-env.eba-vmxfgd5a.us-east-1.elasticbeanstalk.com/coupon/favorite/user/Leilani/item/MLA1140679928' \
	--header 'Content-Type: application/json'	

**Validar estado del servicio**

	curl --location --request GET 'http://springbootapp-env.eba-vmxfgd5a.us-east-1.elasticbeanstalk.com/actuator/health' \
	--header 'Content-Type: application/json' \
	--data-raw ''
