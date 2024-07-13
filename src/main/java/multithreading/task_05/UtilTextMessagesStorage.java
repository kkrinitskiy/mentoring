package multithreading.task_05;

public class UtilTextMessagesStorage {
    public static final String INTRODUCE = """
            *********************************************************************
            * Добро пожаловать в крайне сложный и неэффективный калькулятор -   *
            * очередь. Создаем очередь из операций с числами и выполняем разом. *
            * Сейчас мы займемся вашими вычислениями в режиме многопоточности.  *
            * Формат ввода чисел "123", "123.34", "123,45" - помните об этом.   *
            * Для получения результата просто следуйте дальнейшим инструкциям.  *
            *                                                                   *
            * Для очистки очереди введите "clear"                               *
            * Для выполнения операций из очереди введите "execute"              *
            * Для выхода из приложения введите "exit"                           *
            *********************************************************************
            """;
    public static final String ADD_FIRST_NUMBER_MSG = "- Введите первое число";
    public static final String ADD_SECOND_NUMBER_MSG = "- Введите второе число";
    public static final String ADD_OPERATION_TYPE = """
            - Введите номер операции:
            - 1 - сложение
            - 2 - вычитание
            - 3 - умножение
            - 4 - деление
            """;
    public static final String EXPRESSIONS_IN_QUEUE = "- Операций в очереди: %d\n";
    public static final String START = "- Начинаем выполнение операций";
    public static final String CLEAR_QUEUE = "- Очередь была очищена";
    public static final String INCORRECT_SYMBOL_MSG = "!!! Недопустимый формат ввода. Причина: \"%s\"";
    public static final String EMPTY_QUEUE = "!!! Очередь операций пуста";
}
