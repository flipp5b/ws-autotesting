package com.github.flipp5b.wsautotesting;

import com.eviware.soapui.tools.SoapUITestCaseRunner;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

// Указываем Arquillian в качестве JUnit раннера
@RunWith(Arquillian.class)
public class GreeterIT {
    /*
    Метод, помеченный аннотацией Deployment, формирует архив, который Arquillian будет деплоить
    в контейнер перед выполнением тестов.
    По умолчанию для архива создается обертка, позволяющая выполнить тесты в рамках контейнера.
    В нашем случае в этом необходимости нет, тесты должны запускаться на стороне клиента,
    поэтому выставляем testable в false.
    */
    @Deployment(testable = false)
    public static Archive<?> createDeployment() {
        /*
        ShrinkWrap позволяет филигранно создавать микродеплойменты, изолируя части приложения,
        которые мы хотим протестировать, и уменьшая время деплоя. Но, к сожалению, иногда
        (особенно в больших приложениях с кучей зависимостей) сложно выделить часть для микродеплоймента,
        в таком случае можно просто взять архив, заботливо собранный Maven-ом.
        */
        return ShrinkWrap.createFromZipFile(WebArchive.class, new File("target/ws-autotesting.war"));
    }

    @Test
    public void testGreeter() throws Exception {
        SoapUITestCaseRunner runner = new SoapUITestCaseRunner();
        // Указываем SoapUI путь к файлу проекта
        runner.setProjectFile("src/test/resources/Greeter-soapui-project.xml");
        // просим его создавать отчет в формате JUnit
        runner.setJUnitReport(true);
        // и еще печатать отчет в консоль
        runner.setPrintReport(true);
        // и складывать файлы с отчетами в стандартный для failsafe каталог
        runner.setOutputFolder("target/failsafe-reports");
        runner.run();
    }
}