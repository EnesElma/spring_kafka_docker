## Pom.xml - Dockerfile

- Pom.xml’de finalname ile jar dosya ismi belirtilir
- finalname ile belirtilen jar dosya ismi ile Dockerfile’daki jar dosya isimleri aynı olmalıdır
- Pom.xml içindeki java versiyonu ile Dockerfile içindeki imajların java versiyonları aynı olmalı
- image oluştururken jar dosya yollarına dikkat edilmelidir

## docker-compose.yml dosyası

- versiyon olarak şu anda 3.7 uygun
- servislerin içindeki "build" komutu Dockerfile dosyasının olduğu dizine yönlendirilmelidir, aynı klasörde ise nokta kullanılır
- image Docker Hub'a yüklenecek şekilde isimlendirilecekse "image: image_name" kullanılır.
- container ismi için "container_name" komutu kullanılır
- dışarıya açılan bir servis ise port unutulmamalıdır.
- çoklu image oluşturma aşağıdan yukarıya doğru işler
- çoklu image oluştururken örneğin önce database sonra service oluşturulacaksa "depend_on" kullanılmalıdır. Database oluşmadan servis oluşturulmaz böylece

### docker-compose.yml ile imaj oluşturma ve ayağa kaldırma
```
docker compose up -d
```

### containeri durdurup silme
```
docker compose down
```

### kodlarda değişiklik yapıldıysa yapılacaklar
````
docker compose build
docker compose up -d
````