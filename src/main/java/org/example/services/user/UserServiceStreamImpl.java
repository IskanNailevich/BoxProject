package org.example.services.user;

import org.example.utilClasses.User;
import org.example.services.convert.ListConverter;
import org.example.services.convert.ListConverterImpl;
import org.example.services.workWithFile.WorkWithFIle;
import org.example.services.workWithFile.WorkWithFileImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserServiceStreamImpl implements UserService {
    private InitParams initParams;
    private ValidateParams validateParams;
    private static final String INPUT_CHOICE = "Введите %s для обновления:\n";
    private WorkWithFIle workWithFIleService;
    private ListConverter listConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceStreamImpl.class);

    public UserServiceStreamImpl() {
        workWithFIleService = new WorkWithFileImpl();
        listConverter = new ListConverterImpl();
        initParams = new InitParamsImpl();
        validateParams = new ValidateParamsImpl();
    }

    public void create() {
        LOGGER.info("Начал работу метод по созданию и проверке пользователя!");
        int id = getId();
        createAndValidate(id, initParams.fullNameParams(),
                initParams.birthdayParams(),
                initParams.sexParams());
        LOGGER.info("Метод по созданию и проверке пользователя закончил работу!");
    }

    @Override
    public User get() {
        LOGGER.info("Метод вернул пользователя по ID введенного с консоли!");
        return getById(getIdUserConsole("получить"));

    }

    @Override
    public User getById(int id) {
        LOGGER.info("Начал работу метод по получению пользователя по ID!");
        User user = null;
        List<User> allUsers = getAllUsers();
        for (User elementOfUsers : allUsers) {
            if (elementOfUsers.getId() == id) {
                user = elementOfUsers;
            }
        }
        LOGGER.info("Метод по получению пользователя по ID закончил работу!");
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        LOGGER.info("Начал работу метод по получению всех пользователей!");
        List<String> listFromFile = workWithFIleService.getListFromFile();

        if (listFromFile.get(0).equals("null")) {
            System.out.println("Пользователей в списке нет, список пуст");
        }
        LOGGER.info("Метод по получению всех пользователей закончил работу!");
        return listConverter.stringToUsers(listFromFile);
    }

    public void update() {
        LOGGER.info("Начал работу метод по обновлению данных пользователя!");
        int keyId = getIdUserConsole("обновить");
        User userForUpdate = getById(keyId);

        System.out.println("Что хотите обновить, введите подходяшую цифру:");
        System.out.println("\t1 - Фамилию\n\t2 - Имя\n\t3 - Отчество\n\t4 - Дата рождения\n\t5 - Пол");
        int choice = Integer.parseInt(initParams.readFromConsole());

        updateByChoice(userForUpdate, choice);
        LOGGER.info("Метод по обновлению данных пользователя закончил работу!");
    }

    @Override
    public void delete() {
        LOGGER.info("Начал работу метод по удалению пользователя!");
        int keyId = getIdUserConsole("удалить");
        List<User> usersListAfterDelete = returnListWithoutUser(keyId);
        if (usersListAfterDelete.size() == 0) {
            workWithFIleService.writeUserDataToFile(null, false);
        } else {
            idDecrement(usersListAfterDelete, keyId);
            for (int i = 0; i < usersListAfterDelete.size(); i++) {
                boolean tmp = true;
                if (i == 0) {
                    tmp = false;
                }
                workWithFIleService.writeUserDataToFile(usersListAfterDelete.get(i), tmp);
            }
            LOGGER.info("Метод по удалению пользователя закончил работу!");
        }
    }

    /**
     * Возвращает айди пользователя введеного с консоли.
     * Параметр строка - указываем для каких целей нужно айди.
     *
     * @param s Строка с айди.
     * @return Возвращаемое число.
     */
    private int getIdUserConsole(String s) {
        LOGGER.info("Начал работу метод по введению айди пользователя с консоли!");
        System.out.printf("Введите айди пользователя, которого хотите %s:\n", s);
        LOGGER.info("Метод по введению айди пользователя с консоли закончил работу!");
        return Integer.parseInt(initParams.readFromConsole());
    }

    /**
     * Принимает пользователя, которого надо обновить и выбор цифры с консоли.
     * Создает копию пользователя, которого надо обновить, меняет в нем необходимые данные в зависимости от выбора
     * и отправляет в файл обнолвенный лист сюзеров с копией.
     *
     * @param userForUpdate Пользователь для обновления.
     * @param choice        Параметр для выбора позиции.
     */
    private void updateByChoice(User userForUpdate, int choice) {
        LOGGER.info("Начал работу метод по обновлению данных пользователя введенных с консоли!");
        int updateId = userForUpdate.getId();

        String[] arrayFullName = {userForUpdate.getFirstName(),
                userForUpdate.getFirstName(),
                userForUpdate.getPatronymic()};

        int[] arrayBirthday = {userForUpdate.getBirthdate().getYear(),
                userForUpdate.getBirthdate().getMonthValue(),
                userForUpdate.getBirthdate().getDayOfMonth()};

        String sex = userForUpdate.getSex();

        if (choice == 1) {
            System.out.printf(INPUT_CHOICE, "фамилию");
            arrayFullName[0] = initParams.readFromConsole();
        } else if (choice == 2) {
            System.out.printf(INPUT_CHOICE, "имя");
            arrayFullName[1] = initParams.readFromConsole();
        } else if (choice == 3) {
            System.out.printf(INPUT_CHOICE, "отчество");
            arrayFullName[2] = initParams.readFromConsole();
        } else if (choice == 4) {
            arrayBirthday = initParams.birthdayParams();
        } else if (choice == 5) {
            sex = initParams.sexParams();
        }
        List<User> listWithoutUpdatedUser = returnListWithoutUser(updateId);
        if (listWithoutUpdatedUser.size() == 0) {
            workWithFIleService.writeUserDataToFile(null, false);
            createAndValidate(updateId - 1, arrayFullName, arrayBirthday, sex);
        } else {
            for (int i = 0; i <= listWithoutUpdatedUser.size(); i++) {
                boolean tmp = i != 0;
                if (i == userForUpdate.getId() - 1) {
                    createAndValidate(updateId - 1, arrayFullName, arrayBirthday, sex);
                }
                if (i != listWithoutUpdatedUser.size()) {
                    workWithFIleService.writeUserDataToFile(listWithoutUpdatedUser.get(i), tmp);
                }
            }
        }
        System.out.println("Успешно обновили данные пользователя");
        LOGGER.info("Метод по обновлению данных пользователя введенных с консоли закончил работу!");
    }

    /**
     * Валидация всех параметров, кроме айди.
     * Создает пользователя.
     * Отправка пользователя в файл.
     *
     * @param id            Айди пользователя.
     * @param arrayFullName Массив с ФИО пользователя.
     * @param arrayBirthday Массив с датой рождения.
     * @param sex           Пол пользователя.
     */
    private void createAndValidate(int id, String[] arrayFullName, int[] arrayBirthday, String sex) {
        LOGGER.info("Начал работу метод по овалидации данных пользователя!");
        String[] stringsUserInfo = arrayFullName;
        int[] birthdayUserInfo = arrayBirthday;
        String sexUserInfo = sex;

        while (!validateParams.validateFullNameParams(stringsUserInfo)) {
            System.out.println("Ошибка в ФИО параметрах при создании пользователя");
            stringsUserInfo = initParams.fullNameParams();
        }

        while (!validateParams.validateBirthdayParams(birthdayUserInfo)) {
            System.out.println("Ошибка в дате рождения при создании пользователя");
            birthdayUserInfo = initParams.birthdayParams();
        }

        while (!validateParams.validateSexParams(sexUserInfo)) {
            System.out.println("Ошибка в параметре пола при создании пользователя");
            sexUserInfo = initParams.sexParams();
        }

        User user = new User((id + 1), stringsUserInfo[0],
                stringsUserInfo[1],
                stringsUserInfo[2],
                LocalDate.of(birthdayUserInfo[0], birthdayUserInfo[1], birthdayUserInfo[2]),
                sexUserInfo);
        workWithFIleService.writeUserDataToFile(user, true);
        System.out.println(user);
        LOGGER.info("Метод по овалидации данных пользователя закончил работу!");
    }

    /**
     * В переданном листе уменьшает значение айди всех пользователей, которые идут после указанного id.
     * Уменьшают на 1.
     *
     * @param list Лист пользователей.
     * @param id   Айди пользователя.
     */
    private void idDecrement(List<User> list, int id) {
        LOGGER.info("Начал работу метод по декременту ID пользователя!");
        for (User user : list) {
            if (user.getId() > id) {
                user.setId(user.getId() - 1);
            }
        }
        LOGGER.info("Метод по декременту ID пользователя закончил работу!");
    }

    /**
     * Возвращает последний айди в списке, для добавления пользователя
     *
     * @return Айди пользователя
     */
    private int getId() {
        LOGGER.info("Начал работу метод по возврату ID пользователя!");
        int lastId = 0;
        for (int i = 0; i < getAllUsers().size(); i++) {
            if (i == getAllUsers().size() - 1) {
                lastId = getAllUsers().get(i).getId();
            }
        }
        LOGGER.info("Метод по возврату ID пользователя закончил работу!");
        return lastId;
    }

    /**
     * Возвращает лист пользователей в котором нет пользователя с переданным айди.
     *
     * @param id Айди пользователя.
     * @return Список пользователей.
     */
    private List<User> returnListWithoutUser(int id) {
        LOGGER.info("Начал работу метод по возврату пользователей без пользователя ID которого передан в параметрах метода!");
        User keyUser = getById(id);
        List<User> allUsers = getAllUsers();
        List<User> result = new ArrayList<>();
        for (User allUser : allUsers) {
            if (!allUser.equals(keyUser)) {
                result.add(allUser);
            }
        }
        LOGGER.info("Метод по возврату пользователей без пользователя ID которого передан в параметрах метода закончил работу!");
        return result;
    }
}
