package org.example.services.user;

import org.example.utilClasses.User;
import org.example.services.convert.ListConverter;
import org.example.services.convert.ListConverterImpl;
import org.example.services.workWithFile.WorkWithFIle;
import org.example.services.workWithFile.WorkWithFileImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserServiceStreamImpl implements UserService {
    private InitParams initParams;
    private ValidateParams validateParams;
    private static final String DELIMITER = "_____________________________________________________________________________________________";
    private static final String INPUT_CHOICE = "Введите %s для обновления:\n";
    private WorkWithFIle workWithFIleService;
    private ListConverter listConverter;

    public UserServiceStreamImpl() {
        workWithFIleService = new WorkWithFileImpl();
        listConverter = new ListConverterImpl();
        initParams = new InitParamsImpl();
        validateParams = new ValidateParamsImpl();
    }

    //METHODS FOR CREATE----------------------------------------------------------------------------------------------------------------
    public void create() {
        int id = getId();
        createAndValidate(id, initParams.fullNameParams(),
                initParams.birthdayParams(),
                initParams.sexParams());
    }

    ////METHODS FOR GET----------------------------------------------------------------------------------------------------------------
    @Override
    public User get() {
        return getById(getIdUserConsole("получить"));
    }

    @Override
    public User getById(int id) {
        User user = null;
        List<User> allUsers = getAllUsers();
        //System.out.println("Получили лист со всеми юзерами: " + getAllUsers());
        //System.out.println("Сравниваем параметр пользователя по запрашиваемой фамилии");
        for (User elementOfUsers : allUsers) {
            // System.out.println("Проходимся циклом по списку, элемент из списка = " + elementOfUsers);
            // System.out.println(elementOfUsers.getLastName());
            if (elementOfUsers.getId() == id) {
                user = elementOfUsers;
            }
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<String> listFromFile = workWithFIleService.getListFromFile();
        return listConverter.stringToUsers(listFromFile);
    }

    //METHODS FOR UPDATE----------------------------------------------------------------------------------------------------------------
    public void update() {
        int keyId = getIdUserConsole("обновить");
        User userForUpdate = getById(keyId);

        System.out.println("Что хотите обновить, введите подходяшую цифру:");
        System.out.println("\t1 - Фамилию\n\t2 - Имя\n\t3 - Отчество\n\t4 - Дата рождения\n\t5 - Пол");
        int choice = Integer.parseInt(initParams.readFromConsole());

        updateByChoice(userForUpdate, choice);
    }

    //METHODS FOR DELETE----------------------------------------------------------------------------------------------------------------
    @Override
    public void delete() {
        int keyId = getIdUserConsole("удалить");
        List<User> usersListAfterDelete = returnListWithoutUser(keyId);
        idDecrement(usersListAfterDelete, keyId);
        for (int i = 0; i < usersListAfterDelete.size(); i++) {
            boolean tmp = true;
            if (i == 0) {
                tmp = false;
            }
            workWithFIleService.writeUserDataToFile(usersListAfterDelete.get(i), tmp);
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
        System.out.printf("Введите айди пользователя, которого хотите %s:\n", s);
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
        for (int i = 0; i <= listWithoutUpdatedUser.size(); i++) {
            boolean tmp = i != 0;
            if (i == userForUpdate.getId() - 1) {
                createAndValidate(updateId - 1, arrayFullName, arrayBirthday, sex);
            }
            if (i != listWithoutUpdatedUser.size()) {
                workWithFIleService.writeUserDataToFile(listWithoutUpdatedUser.get(i), tmp);
            }
        }
        System.out.println("Успешно обновили данные пользователя");
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

        //  System.out.println("Все параметры успешно прошли проверку, создаем пользователя");
        User user = new User((id + 1), stringsUserInfo[0],
                stringsUserInfo[1],
                stringsUserInfo[2],
                LocalDate.of(birthdayUserInfo[0], birthdayUserInfo[1], birthdayUserInfo[2]),
                sexUserInfo);
        workWithFIleService.writeUserDataToFile(user, true);
        System.out.println(user);
    }

    /**
     * В переданном листе уменьшает значение айди всех пользователей, которые идут после указанного id.
     * Уменьшают на 1.
     *
     * @param list Лист пользователей.
     * @param id   Айди пользователя.
     */
    private void idDecrement(List<User> list, int id) {
        for (User user : list) {
            if (user.getId() > id) {
                user.setId(user.getId() - 1);
            }
        }
    }

    /**
     * Возвращает последний айди в списке, для добавления пользователя
     *
     * @return Айди пользователя
     */
    private int getId() {
        int lastId = 0;
        for (int i = 0; i < getAllUsers().size(); i++) {
            if (i == getAllUsers().size() - 1) {
                lastId = getAllUsers().get(i).getId();
            }
        }
        return lastId;
    }

    /**
     * Возвращает лиист пользователей в котором нет пользователя с переданным айди.
     *
     * @param id Айди пользователя.
     * @return Список пользователей.
     */
    private List<User> returnListWithoutUser(int id) {
        User keyUser = getById(id);
        List<User> allUsers = getAllUsers();
        List<User> result = new ArrayList<>();
        for (User allUser : allUsers) {
            if (!allUser.equals(keyUser)) {
                result.add(allUser);
            }
        }
        return result;
    }
}
