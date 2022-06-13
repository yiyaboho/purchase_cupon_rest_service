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

![Diagrama de clases](https://user-images.githubusercontent.com/106846429/173349880-49d05c1d-5337-4bb7-8f79-62b6a01a3447.jpg)


**Diagrama de despliegue**

![Cupon de compra-deploy](https://user-images.githubusercontent.com/106846429/173350057-6121d83f-d7f4-435d-ad73-f16be07c0741.jpg)


## Instrucciones

- Clonar el repo

	https://github.com/yiyaboho/purchase_cupon_rest_service.git 

- Generar artefacto

	mvn package
	
- ejecutar la aplicacion: en la carpeta target ejcutar
	
	java -jar purchase_coupon_rest_service-0.0.1.jar


##Variables de ambiente##


Puerto donde sube la aplicacion por defecto esta el puerto 5000

	server.port 


URL de la API de Items para obtener el precio de un item, por defector esta el valor https://api.mercadolibre.com/items

		app.api-items.endpoint


Valores para conexion con la base de datos MySql para consultar los favoritos

	spring.datasource.url - jdbc:mysql://localhost:3306/coupon?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false
	spring.datasource.username - usuario
	spring.datasource.password - password



**TEST Nivel 1:**


	curl --location --request POST 'http://localhost:5000/coupon' \
	--header 'Content-Type: application/json' \
	--data-raw '{
	"item_ids": ["MLA1112351518", "MLA1125397650", "MLA580013811", "MLA757113588", "MLA916058884"],
	"amount": 6000
	}'

**TETS Nivel 2:**


	curl --location --request GET 'http://localhost:5000/coupon/stats' \
	--header 'Content-Type: application/json'


**TEST Nivel 3**


Se publico en AWS, la url es:

	http://Springbootapp-env.eba-w6c2kxqy.us-east-1.elasticbeanstalk.com


**Agregar favoritos:**


	curl --location --request POST 'http://localhost:5000/coupon/favorite/user/tatelite/item/MLA19101769' \
	--header 'Content-Type: application/json'
