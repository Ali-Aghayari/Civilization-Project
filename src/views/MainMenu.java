package views;

import controllers.MainMenuController;
import models.User;
import models.UsersDatabase;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class MainMenu extends Menu {
    private User user;
    private MainMenuController mainMenuController;

    public MainMenu(User user, UsersDatabase usersDatabase) {
        super(usersDatabase);
        this.user = user;
        this.mainMenuController = new MainMenuController(user, usersDatabase);
    }

    @Override
    public void run() {
        Matcher matcher;
        String input;
        while (true) {
            input = super.scanner.nextLine();
            if ((matcher = getCommandMatcher(input, MainMenuCommandsRegex.enterMenu.toString())) != null) {
                goToMenu(matcher);
                if (isUserRemoved())
                    return;
            } else if ((matcher = getCommandMatcher(input, MainMenuCommandsRegex.showScoreBoard.toString())) != null) {
                showScoreBoard();
            } else if ((matcher = getCommandMatcher(input, MainMenuCommandsRegex.exit.toString())) != null) {
                return;
            } else if ((matcher = getCommandMatcher(input, MainMenuCommandsRegex.showMenu.toString())) != null) {
                System.out.println("Main Menu");
            } else if ((matcher = getCommandMatcher(input, MainMenuCommandsRegex.logout.toString())) != null) {
                System.out.println("user logged out successfully!");
                return;
            } else if (input.equals("start game")) {
                PlayGameMenu playGameMenu = new PlayGameMenu(null, usersDatabase);
                playGameMenu.run();
            } else {
                System.out.println("invalid command!");
            }
        }
    }


    public void goToMenu(Matcher matcher) {
        if (mainMenuController.isValidMenu(matcher) != null)
            System.out.println(mainMenuController.isValidMenu(matcher).toString());
        else
            mainMenuController.enterMenu();
    }

    public void showScoreBoard() {
        ArrayList<User> users = new ArrayList<User>(usersDatabase.getUsers());
        users = mainMenuController.sortUsersScores(users);
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + "- " + users.get(i).getNickname() + ": " + users.get(i).getHighScore());
        }
    }

    public boolean isUserRemoved() {
        if (this.usersDatabase.getUserByUsername(this.user.getUsername()) == null)
            return true;
        return false;
    }
}