Kullanılan Teknolojiler:Docker ,Couchbase,JAVA,Spring boot ,Maven ,JUNIT4

Amaç:Amaçlanan CourierLocationRepo classın içinde findNearestStores metodunda db de dataları 
100m içinde olanı full-text-search geospatial index ile hızlı bir şekilde getirmek.1 dakikadan daha uzun süre içinde girmişse bu bilgiyi 
DailyCourierTrackingInfo'a basmak.

Nasıl test edilir:
1.Yöntem :Test paketin içindeki CourierLocationServiceTest classın içnde ki addCourierLocationIfNotEnteredTest metodu çalıştırılır.Bütün süreç kontrol edilir.
2.Yöntem: Postmande 'localhost:8000/courier-location' endpointe aşağıdaki request body kısmına yapıştırlır.Post metodu ile tetiklenir.

Örnek Request:

{   "lat": 50.9923307,   
    "lng": 79.1244229 ,
    "courierId":13123123
    "timestamp":'2020-07-16T15:27:12.232+00:00'
 },  
