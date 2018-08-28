**Тестовое задание
##Реализовать приложение-сервер (http) на языке Java, удовлетворяющий следующим обязательным требованиям:
	1. Запускается в виде standalone-приложение или в качестве web-приложения на сервере Apache Tomcat;
	2. Запросы к серверу имеют стандартный вид: http://<host>:<port>/someServiceName;
	3. Логирование осуществляется (с использованием любых средств, предпочтительно
использование фреймворков) в файл ServerLog.txt;
	4. Все дополнительные параметры, необходимые для работы сервера, считываются
из файла настроек;
	5. Сервер может принимать POST запросы, содержащие json определённого формата
(см. далее);
	6. В зависимости от содержания запроса, сервер выполняет действия:
		a. Json пустой – код возврата 500, соответствующее сообщение об ошибке;
		b. Json не соответствует формату – код возврата 500, соответствующее
содержательное сообщение об ошибке;
		c. Json соответствует формату – код возврата 200, сервер выполняет
преобразование полученного json в формат xml по определённой схеме (см. далее) и сохраняет результирующий xml-файл в специальной директории (настраивается);
		d. На любые другие запросы – код возврата 200, сообщение с кратким описанием принципа работы сервера 
	7. По окончании работы сервера, в логе должна быть также дополнительная статистическая информация – количество запросов, обработанных сервером, количество ошибок и предупреждений.

##Формат входного json
Json представляет собой массив измерений температур (пары дата-температура). Любые элементы json, не описанные ниже, должны игнорироваться при обработке.

~~~~
{
  "measurements": [
    {
		"date": timestamp,
    	"temperature": "t_value",
    	"unit": "C"
	}, 
	{
		"date": timestamp ,
    	"temperature": "t_value"
    },
    {
        "date": timestamp ,
        "temperature": "t_value",
     	"unit": "F"
	},
	{
		"date": timestamp ,
		"temperature": "t_value",
		"unit": "K"
	},
	... 
  ]
}
~~~~
