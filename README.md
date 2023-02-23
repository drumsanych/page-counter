Утилита Page-counter
*******************************************
Описание:

Page-counter - приложение для подсчета количества страниц в документах в структуре папок.
Поддерживаемые типы документов: Word (docx), PDF.	

Реализована на платформе Spring Boot (JavaSE17), собирается при помощи Gradle, запускается в контейнере TomCat из Intellij IDEA

![image](https://user-images.githubusercontent.com/106920502/220988554-a2fabb02-f35d-43e8-8366-1f1151d3735a.png)

Для того, чтобы начать пользоваться приложением, необходимо его запустить,
далее отправить Post-запрос "/api/getDocumentsCount" на порт Вашего хоста (проект запускается на 8089),
в теле запроса поместить JSON в формате:

*******************************************
![image](https://user-images.githubusercontent.com/106920502/220993379-cfc2c04d-308d-4429-9d7b-a1d37431665b.png)

{
    "url" : "C:\\MyDocuments\\etc",
    "formats" : ["docx", "pdf"]
}
*******************************************


После чего ожидается ответ:

*******************************************
![image](https://user-images.githubusercontent.com/106920502/220993501-a9945984-3fc9-4e13-a2e1-e0ed0f43dcb6.png)
*******************************************

В случае проблем запрос вернет ответ с сообщением ошибки:
*******************************************
![image](https://user-images.githubusercontent.com/106920502/220993627-2ccf0a78-e5f9-4678-8c28-0d6a0318d7d9.png)
*******************************************
*******************************************

Для того, чтобы добавить новый формат файлов, необходимо создать новый класс-парсер в пакете tg/alexv100/pagecounter/parsers/implementations,

![image](https://user-images.githubusercontent.com/106920502/220994321-27d37e7c-620a-466f-86be-b84efcb56b38.png)

реализовать интерфейс FileParser, пометить класс аннтоациями @Component и @Qualifier("fileparser"),

![image](https://user-images.githubusercontent.com/106920502/220995971-71d35b9c-c6b4-41fe-98f4-430114922f98.png)

после перезапуска в утилите появится парсер нового формата.


Спасибо за внимание)
