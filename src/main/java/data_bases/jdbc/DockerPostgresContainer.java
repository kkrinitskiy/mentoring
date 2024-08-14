package data_bases.jdbc;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.Ports;
import com.github.dockerjava.core.DockerClientBuilder;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DockerPostgresContainer {

    public static final String url = "jdbc:postgresql://localhost:5432/";

    public static final String user = "psql_user";
    public static final String password = "111111";
    public static final String db_name = "db_name";


    public static final String databaseCommand = "postgres";
    public static final String containerName = "jdbc_postgres";
    public static final ExposedPort tcp5432 = ExposedPort.tcp(5432);
    public static final List<String> envs = List.of(
            "POSTGRES_USER=" + user,
            "POSTGRES_PASSWORD=" + password,
            "POSTGRES_DB=" + db_name
    );

    public static void init() {
        try(DockerClient client = DockerClientBuilder.getInstance().build()){

            checkContainerIsExited(client);

        if(!checkContainerExists(client)) {
            System.out.println("Данный контейнер не существует. Создаем");
            Ports portBindings = new Ports();
            portBindings.bind(tcp5432, Ports.Binding.bindPort(5432));

            client.createContainerCmd(databaseCommand)
                    .withName(containerName)
                    .withEnv(envs)
                    .withExposedPorts(tcp5432)
                    .withHostConfig(
                            HostConfig.newHostConfig()
                            .withPortBindings(portBindings)
                    )
                    .exec();
            client.startContainerCmd(containerName).exec();
            System.out.printf("Контейнер %s создан и запущен\n", containerName);
            return;
        }

        System.out.printf("Контейнер %s существует", containerName);

        if(checkContainerIsExited(client)) {
            System.out.println(", но был остановлен. Запускаем");
            client.startContainerCmd(containerName).exec();
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println("Контейнер готов");
            return;
        }

            System.out.println(" и готов к работе");
        }catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean checkContainerExists(DockerClient client) {
        return client.listContainersCmd()
                .withNameFilter(List.of(containerName))
                .withShowAll(true)
                .exec()
                .stream()
                .anyMatch(container -> container.getNames()[0].equals("/" + containerName));
    }

    public static boolean checkContainerIsExited(DockerClient client) {
            return client.listContainersCmd().withStatusFilter(List.of("exited")).withNameFilter(List.of(containerName)).withShowAll(true).exec().stream()
                .anyMatch(container -> container.getNames()[0].equals("/" + containerName));
    }

}
