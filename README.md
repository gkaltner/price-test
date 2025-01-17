# Java Test
En la base de datos de comercio electrónico de la compañía disponemos de la tabla PRICES que refleja el precio final (pvp) y la tarifa que aplica a un producto de una cadena entre unas fechas determinadas.
A continuación se muestra un ejemplo de la tabla con los campos relevantes:

### PRICES

|BRAND_ID |    START_DATE        |        END_DATE       | PRICE_LIST |  PRODUCT_ID | PRIORITY |   PRICE |   CURR | 
|---------|----------------------|-----------------------|------------|-------------|----------|---------|--------|
|1        | 2020-06-14-00.00.00  |  2020-12-31-23.59.59  |      1     |   35455     |    0     |   35.50 |    EUR |
|1        | 2020-06-14-15.00.00  |  2020-06-14-18.30.00  |      2     |   35455     |    1     |   25.45 |    EUR |
|1        | 2020-06-15-00.00.00  |  2020-06-15-11.00.00  |      3     |   35455     |    1     |   30.50 |    EUR |
|1        | 2020-06-15-16.00.00  |  2020-12-31-23.59.59  |      4     |   35455     |    1     |   38.95 |    EUR |

### Campos:

**BRAND_ID**: foreign key de la cadena del grupo (1 = ZARA).

**START_DATE** **END_DATE**: rango de fechas en el que aplica el precio tarifa indicado.

**PRICE_LIST**: Identificador de la tarifa de precios aplicable.

**PRODUCT_ID**: Identificador código de producto.

**PRIORITY**: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico).

**PRICE**: precio final de venta.

**CURR**: iso de la moneda.

### Se pide:

Construir una aplicación/servicio en SpringBoot que provea una end point rest de consulta  tal que:

Acepte como parámetros de entrada: fecha de aplicación, identificador de producto, identificador de cadena.
Devuelva como datos de salida: identificador de producto, identificador de cadena, tarifa a aplicar, fechas de aplicación y precio final a aplicar.

Se debe utilizar una base de datos en memoria (tipo h2) e inicializar con los datos del ejemplo, (se pueden cambiar el nombre de los campos y añadir otros nuevos si se quiere, elegir el tipo de dato que se considere adecuado para los mismos).

Desarrollar unos test al endpoint rest que validen las siguientes peticiones al servicio con los datos del ejemplo:

- Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
- Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
- Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
- Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
- Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)


Se valorará:

1. Diseño y construcción del servicio:
   * Arquitectura hexagonal mediante el uso de módulos o paquetes.
   * Correcta nomenclatura de los componentes.
   * Orientación funcional con el uso de características de Java 17.
   * Swagger/OpenApi
2. Calidad de Código.
   * Principios SOLID.
   * Seguir prácticas de clean code.
3. Resultados correctos en los test:
   * Pruebas unitarias con Junit 5 y Mockito.
   * Pruebas de integración.

## Configuration
1. [Java 17](https://openjdk.org/)
2. [Maven](https://maven.apache.org/) (ya se encuentra embebido en el proyecto)

## Iniciar servicio
Para iniciar el servicio primero se debe generar el jar

```shell
./mvnw clean istall
```
Luego ejecutar el siguiente comando para poder iniciar el servicio.

```shell
java -jar target/prices-1.0-SNAPSHOT.jar
```

## Documentacion API

Para poder acceder a la documentacion de la API, primero se  debe iniciar el servicio
y luego se debe acceder a esta url
http://localhost:8080/v3/swagger-ui/index.html

or you can execute the following request to see the result:
```shell
curl -X 'GET' \
  'http://localhost:8080/prices?dateTime=2020-06-16T10%3A38%3A48&product=35455&brandId=1' \
  -H 'accept: application/json'
```