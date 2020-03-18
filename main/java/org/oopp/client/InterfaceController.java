package org.oopp.client;

import animatefx.animation.BounceIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbarLayout;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleNode;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.weathericons.WeatherIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.oopp.server.Message;
import org.oopp.server.database.Activity;
import org.oopp.server.database.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class InterfaceController implements Initializable {

    public static String url = Url.url;

    @FXML
    private PieChart pieTest;

    @FXML
    private EventHandler handler = new EventHandler() {
        @Override
        public void handle(Event event) {
            snackbar.close();
        }
    };

    @FXML
    private StackPane stackSnackbar;

    @FXML
    Image img = new Image("org/oopp/client/images/shrek.png");

    @FXML
    private ImageView b1;

    @FXML
    private ImageView b2;

    @FXML
    private ImageView b3;

    @FXML
    private ImageView b4;

    @FXML
    private ImageView b5;

    @FXML
    private ImageView b6;

    @FXML
    private Label label;

    @FXML
    private Label carbonSaved;

    @FXML
    private Label waterSaved;

    @FXML
    private Label landSaved;

    @FXML
    private Label userName;

    @FXML
    private Label friendName;

    @FXML
    private Label carbonSavedFriend;

    @FXML
    private Label waterSavedFriend;

    @FXML
    private Label landSavedFriend;

    @FXML
    private Label statsCarbon;

    @FXML
    private Label statsWater;

    @FXML
    private Label statsLand;

    @FXML
    private Label statsBicycle;

    @FXML
    private Label statsTransport;

    @FXML
    private Label statsVegan;

    @FXML
    private Label statsVegetarian;

    @FXML
    private Label statsLocal;

    @FXML
    private Label statsTrees;

    @FXML
    private Label statsSolar;

    @FXML
    private Circle userImage;

    @FXML
    private Circle friendImage;

    @FXML
    private BorderPane borderPane;

    @FXML
    private AnchorPane paneLogin;

    @FXML
    private AnchorPane paneDashboard;

    @FXML
    private AnchorPane paneAchievements;

    @FXML
    private AnchorPane paneStatistics;

    @FXML
    private AnchorPane paneData;

    @FXML
    private AnchorPane paneCredits;

    @FXML
    private AnchorPane paneFriend;

    @FXML
    private JFXButton buttonDashboard;

    @FXML
    private JFXButton buttonAchievements;

    @FXML
    private JFXButton buttonStatistics;

    @FXML
    private JFXButton buttonData;

    @FXML
    private JFXButton buttonCredits;

    @FXML
    private JFXButton buttonAddMeal;

    @FXML
    private JFXButton buttonAddTravel;

    @FXML
    private JFXButton buttonPlantTree;

    @FXML
    private JFXButton buttonRemove;

    @FXML
    private JFXButton buttonSearch;

    @FXML
    private JFXButton buttonAddFriend;

    @FXML
    private JFXButton buttonAccept;

    @FXML
    private JFXButton buttonDecline;

    @FXML
    private JFXButton buttonCheckFriend;

    @FXML
    private JFXButton buttonBack;

    @FXML
    private JFXButton buttonMore;

    @FXML
    private JFXButton buttonRemoveFriend;

    @FXML
    private JFXButton buttonLowerTemp;

    @FXML
    private  JFXButton buttonAddPanel;

    @FXML
    private ToggleGroup mealBefore;

    @FXML
    private ToggleGroup mealAfter;

    @FXML
    private ToggleGroup travelAfter;

    @FXML
    private ToggleGroup homePower;

    @FXML
    private JFXTextField fieldDistance;

    @FXML
    private TextField fieldSearch;

    @FXML
    private JFXTextField weeks;

    @FXML
    private JFXTextField fieldSurface;

    @FXML
    private JFXSlider sliderTemp;

    @FXML
    private JFXListView<Label> historyList;

    @FXML
    private JFXListView<Label> searchList;

    @FXML
    private JFXListView<Label> requestList;

    @FXML
    private JFXListView<Label> friendList;

    @FXML
    private JFXSnackbarLayout activityAdded = new JFXSnackbarLayout(
            "activity added", "ok", handler);

    @FXML
    private JFXSnackbar.SnackbarEvent snackbarAdded;

    @FXML
    private JFXSnackbar.SnackbarEvent snackbarRemoved;

    @FXML
    private JFXSnackbarLayout activityAddException = new JFXSnackbarLayout(
            "please fill in all fields correctly", "ok", handler);


    @FXML
    private JFXSnackbarLayout activityRemoved = new JFXSnackbarLayout(
            "activity removed", "ok", handler);

    @FXML
    private JFXSnackbarLayout connectionSuccess = new JFXSnackbarLayout(
            "your data was retrieved", "ok", handler);

    @FXML
    private JFXSnackbarLayout connectionFail = new JFXSnackbarLayout(
            "your data could not be retrieved", "ok", handler);

    @FXML
    private JFXSnackbarLayout noConnection = new JFXSnackbarLayout(
            "a connection to the server is necessary", "ok", handler);

    @FXML
    private JFXSnackbarLayout newFriendRequest = new JFXSnackbarLayout(
            "you have pending friend requests", "ok", handler
    );

    @FXML
    private JFXSnackbarLayout sentFriendRequest = new JFXSnackbarLayout(
            "friend request sent", "ok", handler
    );

    @FXML
    private JFXSnackbarLayout acceptFriendRequest = new JFXSnackbarLayout(
            "friend added", "ok", handler
    );

    @FXML
    private JFXSnackbar snackbar;

    @FXML
    private WebView googleLoginPage;

    @FXML
    private JFXButton googleLoginButton;

    @FXML
    private WebEngine webEngine;

    @FXML
    private JFXCheckBox checkLocal;

    @FXML
    private JFXComboBox<String> travelBefore;

    private ArrayList<Activity> activities;

    private ArrayList<User> friends;

    private ArrayList<User> searchResults;

    private ArrayList<User> friendRequests = new ArrayList<>();

    private ArrayList<Integer> badges;

    private ShowHistory historyTask = new ShowHistory();

    private Service<ArrayList<User>> showFriendsThread;

    private Service<ArrayList<User>> showRequestsThread;

    private User user;

    private String[] months = {"Jan", "Feb", "Mar",
        "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};

    private ArrayList<String> food = new ArrayList<>(Arrays.asList("meat", "pescetarian",
            "vegetarian", "vegan"));

    private ArrayList<String> travel = new ArrayList<>(Arrays.asList("car", "hybrid", "electric",
            "plane", "train", "bus", "bike"));

    private  ArrayList<String> home = new ArrayList<>(Arrays.asList("solar", "gas", "electricity"));

    private Map<String, String> travelMapA = new HashMap<String, String>() {
        {
            put("car", "gas car");
            put("hybrid", "hybrid car");
            put("electric", "electric car");
            put("plane", "plane");
            put("train", "train");
            put("bus", "bus");
            put("bike", "bike");
        }
    };

    private Map<String, String> travelMapB = new HashMap<String, String>() {
        {
            put("gas car", "car");
            put("hybrid car", "hybrid");
            put("electric car", "electric");
            put("plane", "plane");
            put("train", "train");
            put("bus", "bus");
            put("bike", "bike");
        }
    };

    private int carbonFood = 0;
    private int carbonTravel = 0;
    private int carbonHome = 0;

    private ObservableList<PieChart.Data> pieChartData =
            FXCollections.observableArrayList(
                    new PieChart.Data("food", carbonFood),
                    new PieChart.Data("travel", carbonTravel),
                    new PieChart.Data("home", carbonHome));


    /**
     * Action after a button is clicked. The type of button is established and the according
     * action is taken.
     *
     * @param event The event created when a button is clicked.
     */
    @FXML
    private void handleButtonAction(ActionEvent event) {

        if (event.getSource() == buttonDashboard) {
            paneDashboard.toFront();
        } else if (event.getSource() == buttonData) {
            paneData.toFront();
        } else if (event.getSource() == buttonCredits) {
            paneCredits.toFront();
        } else if (event.getSource() == buttonAchievements) {
            paneAchievements.toFront();
        } else if (event.getSource() == buttonStatistics) {
            computeSavings(activities);
            showStats(user);
            paneStatistics.toFront();
        } else if (event.getSource() == buttonAddMeal && testServer()) {
            handleAddMeal();
        } else if (event.getSource() == buttonAddTravel && testServer()) {
            handleAddTravel();
        } else if (event.getSource() == buttonRemove && !historyIsEmpty() && historyIsSelected()
                && testServer()) {

            // Get the index of the currently selected item.
            int index = historyList.getSelectionModel().getSelectedIndex();

            // Remove the meal.
            removeActivity(index);

            // Show that the activity was successfully removed.
            snackbar.enqueue(snackbarRemoved);
        } else if (event.getSource() == googleLoginButton && testServer()) {
            handleGoogle();
        } else if (event.getSource() == buttonSearch && testServer()) {
            handleSearch();
        } else if (event.getSource() == buttonAddFriend && testServer()) {
            if (!searchList.getItems().isEmpty() && searchIsSelected()) {
                UserInterface.sendRequest(user, UserInterface.getUser(
                        searchList.getSelectionModel().getSelectedItem().getId()));
                searchList.getItems().clear();
            }
        } else if (event.getSource() == buttonAccept && testServer()) {
            User friend = UserInterface.getUser(
                    requestList.getSelectionModel().getSelectedItem().getId());
            UserInterface.acceptFriendship(user, friend);
            requestList.getItems().clear();
        } else if (event.getSource() == buttonDecline && testServer()) {
            User friend = UserInterface.getUser(
                    requestList.getSelectionModel().getSelectedItem().getId());
            UserInterface.deleteRequest(user, friend);
            requestList.getItems().clear();
        } else if (event.getSource() == buttonRemoveFriend && testServer()) {
            User friend = UserInterface.getUser(
                    friendList.getSelectionModel().getSelectedItem().getId());
            showFriends(UserInterface.deleteFriend(user, friend));
        } else if (event.getSource() == buttonCheckFriend && friendIsSelected()) {

            User friend = UserInterface.getUser(
                    friendList.getSelectionModel().getSelectedItem().getId());

            friendName.setText(friend.getFullName());
            carbonSavedFriend.setText(friend.getTotalEmissionSaved() + " kg");
            waterSavedFriend.setText(friend.getTotalWaterSaved() + " L");
            landSavedFriend.setText(friend.getTotalLandSaved() + " m2");
            friendImage.setFill(new ImagePattern(new Image(friend.getPhoto())));

            paneFriend.toFront();
        } else if (event.getSource() == buttonMore && testServer()) {
            User friend = UserInterface.getUser(
                    friendList.getSelectionModel().getSelectedItem().getId());

            System.out.println(friend.getUserId());

            ArrayList<Activity> friendActivities =
                    ActivityInterface.getActivitiesUser(friend.getUserId());

            computeSavings(friendActivities);
            showStats(friend);
            paneStatistics.toFront();
        } else if (event.getSource() == buttonBack) {
            paneFriend.toBack();
        } else if (event.getSource() == buttonPlantTree && testServer()) {
            addTree(user);
        } else if (event.getSource() == buttonLowerTemp && testServer()) {
            handleLowerTemp();
        } else if (event.getSource() == buttonAddPanel && testServer()) {
            handleAddPanel();
        }

    }

    /**
     * Perform all necessary actions to lower temperature.
     */
    private void handleLowerTemp() {
        try {
            String numOfWeeks = weeks.getText();

            JFXToggleNode energySource = (JFXToggleNode) homePower.getSelectedToggle();
            Activity activity = ActivityInterface.addActivity(energySource.getId(),
                    Double.toString(sliderTemp.getValue()), user, numOfWeeks);
            activities.add(activity);

            createHistoryLabel("home", activity);
            historyList.getItems().add(label);
            historyList.getSelectionModel().selectLast();

            increaseSavings(activity);
            showSavings();

            snackbar.enqueue(snackbarAdded);
        } catch (NumberFormatException | NullPointerException e) {
            snackbar.enqueue(new JFXSnackbar.SnackbarEvent(activityAddException));
        }
    }

    /**
     * Perform all the necessary actions to add solar panels.
     */
    private void handleAddPanel() {
        try {

            String surface = fieldSurface.getText();

            Activity activity = ActivityInterface.addActivity("s_solar", "s_solar",
                    user, surface);
            activities.add(activity);

            createHistoryLabel("solar", activity);
            historyList.getItems().add(label);
            historyList.getSelectionModel().selectLast();

            increaseSavings(activity);
            showSavings();

            snackbar.enqueue(snackbarAdded);
        } catch (NumberFormatException e) {
            snackbar.enqueue(new JFXSnackbar.SnackbarEvent(activityAddException));
        }
    }

    /**
     * Performs the necessary actions for when a search is performed.
     */
    private void handleSearch() {

        // Clear current list.
        searchList.getItems().clear();

        // Only search if the text field is not empty.
        if (!fieldSearch.getText().isEmpty()) {
            searchResults = UserInterface.findUsersByName(fieldSearch.getText());
            ArrayList<User> pendingFriends = UserInterface.getPendingFriends(user);
            ArrayList<User> receivedRequests = UserInterface.getFriendRequests(user);

            for (User result : searchResults) {

                // Exclude self, sent requests and current friends.
                if (!user.getUserId().equals(result.getUserId()) && !pendingFriends.contains(result)
                        && !receivedRequests.contains(result)
                        && !UserInterface.areFriends(user, result)) {
                    Label resultLabel = new Label(result.getFullName() + " | "
                            + result.getUserName());
                    resultLabel.setId(result.getUserId());
                    searchList.getItems().add(resultLabel);
                }
            }
        }
    }

    /**
     * Performs the necessary actions for when a meal is added.
     */
    private void handleAddMeal() {
        try {

            // Get the selected nodes.
            JFXToggleNode before = (JFXToggleNode) mealBefore.getSelectedToggle();
            JFXToggleNode after = (JFXToggleNode) mealAfter.getSelectedToggle();

            String foodBefore = before.getId();
            String foodAfter = after.getId();
            foodAfter = foodAfter.substring(0, foodAfter.length() - 1);

            // Add the meal.
            addMeal(foodBefore, foodAfter, user);

            // Show that the activity was successfully added.
            snackbar.enqueue(snackbarAdded);
        } catch (NullPointerException e) {
            snackbar.enqueue(new JFXSnackbar.SnackbarEvent(activityAddException));
        }
    }

    /**
     * Performs the necessary actions when adding a new travel activity.
     */
    private void handleAddTravel() {
        try {

            System.out.println(fieldDistance.getText());

            // Get the selected nodes.
            JFXToggleNode after = (JFXToggleNode) travelAfter.getSelectedToggle();

            String travelAfter = after.getId();
            String travelBefore = "t_" + travelMapB.get(
                    this.travelBefore.getSelectionModel().getSelectedItem());

            // Add the travel activity.
            addTravel(travelBefore, travelAfter, user);

            // Show that the activity was successfully added.
            snackbar.enqueue(snackbarAdded);
        } catch (NumberFormatException | NullPointerException e) {
            snackbar.enqueue(new JFXSnackbar.SnackbarEvent(activityAddException));
        }
    }

    /**
     * Performs the necessary actions when logging in with Google.
     */
    private void handleGoogle() {
        googleLoginPage.setVisible(true);
        webEngine.load(url + "/newuser");

        webEngine.getLoadWorker().stateProperty().addListener((observable, oldVal, newVal) -> {
            if (Worker.State.SUCCEEDED.equals(newVal)
                    && webEngine.getLocation().contains("newuser")) {
                googleLoginPage.setVisible(false);
                paneLogin.toBack();

                // Set the user ID in the task and run the thread.
                user = UserInterface.getUser(UserInterface.getUserId());
                historyTask.setUserId(UserInterface.getUserId());
                new Thread(historyTask).start();

                // Set the user name and image
                img = new Image(user.getPhoto());
                userImage.setFill(new ImagePattern(img));
                userName.setText(user.getFullName());

                setBadges(user);

                showRequestsThread = new Service<ArrayList<User>>() {
                    @Override
                    protected Task<ArrayList<User>> createTask() {
                        return new ShowRequests(user);
                    }
                };

                showRequestsThread.setOnFailed(new EventHandler<WorkerStateEvent>() {
                    @Override
                    public void handle(WorkerStateEvent event) {
                        showRequestsThread.restart();
                    }
                });

                showRequestsThread.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                    @Override
                    public void handle(WorkerStateEvent event) {

                        friendRequests = showRequestsThread.getValue();

                        if (friendRequests != null) {
                            showRequests(friendRequests);
                        }
                        showRequestsThread.restart();

                    }
                });

                showRequestsThread.start();

                showFriendsThread = new Service<ArrayList<User>>() {
                    @Override
                    protected Task<ArrayList<User>> createTask() {
                        return new ShowFriends(user);
                    }
                };

                showFriendsThread.setOnFailed(new EventHandler<WorkerStateEvent>() {
                    @Override
                    public void handle(WorkerStateEvent event) {
                        showFriendsThread.restart();
                    }
                });

                showFriendsThread.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                    @Override
                    public void handle(WorkerStateEvent event) {

                        friends = showFriendsThread.getValue();

                        if (friends != null) {
                            showFriends(friends);
                        }

                        showFriendsThread.restart();

                    }
                });

                showFriendsThread.start();
            }
        });
    }

    /**
     * Display the friend requests.
     * @param requests A list of users.
     */
    private void showRequests(ArrayList<User> requests) {

        int selectedIndex = -1;

        if (requestIsSelected()) {
            selectedIndex = requestList.getSelectionModel().getSelectedIndex();
        }

        requestList.getItems().clear();


        if (!requests.isEmpty()) {
            for (User request : requests) {
                Label requestLabel = new Label(request.getFullName());
                requestLabel.setId(request.getUserId());
                requestList.getItems().add(requestLabel);
            }
        }

        if (selectedIndex >= 0) {
            requestList.getSelectionModel().select(selectedIndex);
        }
    }

    /**
     * Display the friend list.
     * @param friends A list of users.
     */
    private void showFriends(ArrayList<User> friends) {

        int selectedIndex = -1;

        if (friendIsSelected()) {
            selectedIndex = friendList.getSelectionModel().getSelectedIndex();
        }

        friendList.getItems().clear();

        if (!friends.isEmpty()) {
            for (User friend : friends) {
                Label friendLabel = new Label(friend.getFullName());
                friendLabel.setId(friend.getUserId());
                friendList.getItems().add(friendLabel);
            }
        }

        if (selectedIndex >= 0) {
            friendList.getSelectionModel().select(selectedIndex);
        }
    }

    /**
     * Checks whether the history list is empty.
     *
     * @return true when the list is empty.
     */
    private boolean historyIsEmpty() {
        return historyList.getItems().size() == 0;
    }

    /**
     * Checks whether there is something selected in the history list.
     *
     * @return true if something in the history list is selected.
     */
    private boolean historyIsSelected() {
        return historyList.getSelectionModel().getSelectedIndices().size() > 0;
    }

    /**
     * Checks whether there is something selected in the friend requests.
     * @return true if something is selected in the friend requests.
     */
    private boolean requestIsSelected() {
        return requestList.getSelectionModel().getSelectedIndices().size() > 0 ;
    }

    /**
     * Checks whether the is something selected in the search list.
     * @return true if something in the search list is selected.
     */
    private boolean searchIsSelected() {
        return searchList.getSelectionModel().getSelectedIndices().size() > 0;
    }

    /**
     * Checks whetehr something is selected in the friends list.
     * @return true if something is selected in the friend list.
     */
    private boolean friendIsSelected() {
        return friendList.getSelectionModel().getSelectedIndices().size() > 0;
    }

    /**
     * Adds a meal activity to the database for a particular user, adds it to the history list,
     * adds the activity to the local history, updates the selection and shows the savings.
     *
     * @param mealBefore A string of the meal that would have been eaten.
     * @param mealAfter  A string of the meal that was eaten.
     * @param user       The user for which the activity is to be added.
     */
    private void addMeal(String mealBefore, String mealAfter, User user) {

        // Add the activity to the database.
        Activity activity = ActivityInterface.addActivity(mealAfter, mealBefore,
                user, checkLocal.isSelected());
        activities.add(activity);

        // Create label and add it to the history
        createHistoryLabel("food", activity);
        historyList.getItems().add(label);
        historyList.getSelectionModel().selectLast();

        // Update the savings.
        increaseSavings(activity);
        showSavings();
    }

    /**
     * Adds a travel activity to the database for a particular user, adds it to the history list
     * updates the selection and shows the savings.
     * @param travelBefore The transportation method that was planned.
     * @param travelAfter The transportation method that was actually used.
     * @param user The user for which the activity is to be added.
     */
    private void addTravel(String travelBefore, String travelAfter, User user) {

        // Add the activity to the database.
        Activity activity = ActivityInterface.addActivity(travelAfter,
                travelBefore, user, Integer.parseInt(fieldDistance.getText()));
        activities.add(activity);

        // Create label and add it to the history
        createHistoryLabel("travel", activity);
        historyList.getItems().add(label);
        historyList.getSelectionModel().selectLast();

        // Update the savings.
        increaseSavings(activity);
        showSavings();
    }

    /**
     * Adds a tree planting activity to the user, adds it to the history list and
     * updates the selection.
     * @param user The user for which to add a tree.
     */
    private void addTree(User user) {
        Activity activity = ActivityInterface.addActivity("p_tree", "p_tree", user);
        activities.add(activity);

        createHistoryLabel("tree", activity);
        historyList.getItems().add(label);
        historyList.getSelectionModel().selectLast();

        increaseSavings(activity);
    }

    /**
     * Removes the selected activity from the database, the history list,
     * removes the activities from the local activities ArrayList
     * and clears the selection.
     *
     * @param index The index of the activity to be removed.
     */
    private void removeActivity(int index) {

        // Remove the activity frm the database.
        int activityId = Integer.parseInt(historyList.getItems().get(index).getId());
        ActivityInterface.deleteActivity(activityId);

        // Update the history list.
        historyList.getItems().remove(index);
        historyList.getSelectionModel().select(historyList.getItems().size() - 1);

        // Update the savings.
        Activity activity = activities.remove(index);
        decreaseSavings(activity);
        showSavings();
    }

    /** This method creates a label that is added to the history list.
     * @param activityType The type of activity for which a label must be created.
     * @param activity     The activity for which the label must be created.
     */
    private void createHistoryLabel(String activityType, Activity activity) {

        // Format date
        Calendar cal = Calendar.getInstance();
        cal.setTime(activity.getDate());
        String date = cal.get(Calendar.DAY_OF_MONTH) + " "
                + months[cal.get(Calendar.MONTH)] + " " + cal.get(Calendar.YEAR);

        // Create icon
        FontAwesomeIconView icon;
        WeatherIconView weatherIcon;

        // Create label depending on the type of activity
        switch (activityType) {
            case "food":

                String meal;

                if (activity.getName().equals("meat")) {
                    meal = "carnivorous";
                } else {
                    meal = activity.getName();
                }

                // Check if the meal is local
                String local = "";
                if (checkLocal.isSelected()) {
                    local = "local";
                }

                // Create the label
                label = new Label("   " + local + " " + meal + " meal on " + date);
                label.setId(String.valueOf(activity.getActivityId()));
                label.setTooltip(new Tooltip(String.format("emissions: %.2f "
                                + "kg\nwater: %.2f L\nland: %.2f m2", activity.getEmissionSaving(),
                        activity.getWaterSaving(), activity.getLandSaving())));

                // Set the icon
                icon = new FontAwesomeIconView();
                icon.setGlyphName("CUTLERY");
                icon.getStyleClass().add("list-icon");
                label.setGraphic(icon);

                break;

            case "travel":

                // Get the distance traveled
                String distance = Integer.toString(activity.getTravelDistance());

                // Create the label
                label = new Label("   " + "traveled " + distance
                        + " km by " + travelMapA.get(activity.getName())
                        + " on " + date);
                label.setId(String.valueOf(activity.getActivityId()));
                label.setTooltip(new Tooltip(String.format("emissions: %.2f " + "kg",
                        activity.getEmissionSaving())));

                // Set the icon
                icon = new FontAwesomeIconView();
                icon.setGlyphName("BICYCLE");
                icon.getStyleClass().add("list-icon");
                label.setGraphic(icon);

                break;

            case "tree":
                // Create the label
                label = new Label("   planted a tree on " + date);
                label.setId(String.valueOf(activity.getActivityId()));

                // Set the icon
                icon = new FontAwesomeIconView();
                icon.setGlyphName("TREE");
                icon.getStyleClass().add("list-icon");
                label.setGraphic(icon);

                break;

            case "home":

                // Create the label
                label = new Label("   lowered home temperature on " + date);
                label.setId(String.valueOf(activity.getActivityId()));
                label.setTooltip(new Tooltip(String.format("emissions: %.2f " + "kg",
                        activity.getEmissionSaving())));

                // Set the icon
                weatherIcon = new WeatherIconView();
                weatherIcon.setGlyphName("THERMOMETER");
                weatherIcon.getStyleClass().add("list-icon");
                label.setGraphic(weatherIcon);

                break;

            case "solar":

                // Create the label
                label = new Label("   installed solar panels on " + date);
                label.setId(String.valueOf(activity.getActivityId()));
                label.setTooltip(new Tooltip(String.format("emissions: %.2f " + "kg",
                        activity.getEmissionSaving())));

                // Set the icon
                weatherIcon = new WeatherIconView();
                weatherIcon.setGlyphName("YAHOO_32");
                weatherIcon.getStyleClass().add("list-icon");
                label.setGraphic(weatherIcon);

                break;

            default:

                break;
        }
    }

    /**
     * Fills the history list with the activities from the database.
     */
    private void showHistory() {
        for (Activity activity : activities) {

            if (food.contains(activity.getName())) {
                createHistoryLabel("food", activity);
            }

            if (travel.contains(activity.getName())) {
                createHistoryLabel("travel", activity);
            }

            if (activity.getName().equals("tree")) {
                createHistoryLabel("tree", activity);
            }

            if (activity.getName().equals("electricity") || activity.getName().equals("gas")) {
                createHistoryLabel("home", activity);
            }

            if (activity.getName().equals("solar")) {
                createHistoryLabel("solar", activity);
            }

            historyList.getItems().add(label);
        }

        historyList.getSelectionModel().selectLast();
    }

    /**
     * Sets the savings labels to the most recent values.
     */
    private void showSavings() {
        carbonSaved.setText(user.getTotalEmissionSaved() + " kg");
        new BounceIn(carbonSaved).play();
        waterSaved.setText(user.getTotalWaterSaved() + " L");
        new BounceIn(waterSaved).play();
        landSaved.setText(user.getTotalLandSaved() + " m2");
        new BounceIn(landSaved).play();
    }

    /**
     * Increases the savings based on the activity.
     *
     * @param activity The activity holding the emission savings.
     */
    private void increaseSavings(Activity activity) {

        String activityName = activity.getName();
        int distance;

        // Check the kind of activity added and update the user accordingly.
        if (activityName.equals("bus") || activityName.equals("train")) {
            distance = Integer.parseInt(fieldDistance.getText());
            user.setKmPublicTransport(user.getKmPublicTransport() + distance);
        } else if (activityName.equals("bike")) {
            distance = Integer.parseInt(fieldDistance.getText());
            user.setKmCycled(user.getKmCycled() + distance);
        } else if (activityName.equals("pescetarian")) {
            user.setNumPescMeals(user.getNumPescMeals() + 1);
        } else if (activityName.equals("vegetarian")) {
            user.setNumVegMeals(user.getNumVegMeals() + 1);
        } else if (activityName.equals("vegan")) {
            user.setNumVeganMeals(user.getNumVeganMeals() + 1);
        } else if (activityName.equals("tree")) {
            user.setNumTreesPlanted(user.getNumTreesPlanted() + 1);
        } else if (activityName.equals("solar")) {
            user.setNumSolarPanels(user.getNumSolarPanels() + activity.getPanelSurface());
        }

        if (activity.getLocalMeals() > 0) {
            user.setNumLocalMeals(user.getNumLocalMeals() + 1);
        }

        // Always update the emission savings.
        user.setTotalEmissionSaved(user.getTotalEmissionSaved()
                + (int) Math.round(activity.getEmissionSaving()));
        user.setTotalWaterSaved(user.getTotalWaterSaved() + (int) Math.round(activity.getWaterSaving()));
        user.setTotalLandSaved(user.getTotalLandSaved() + (int) Math.round(activity.getLandSaving()));

        UserInterface.updateUser(user);
        setBadges(user);
    }

    /**
     * Decreases the savings based on the activity.
     *
     * @param activity The activity holding the emission savings.
     */
    private void decreaseSavings(Activity activity) {

        String activityName = activity.getName();
        int distance;

        // Check the kind of activity added and update the user accordingly.
        if (activityName.equals("bus") || activityName.equals("train")) {
            distance = activity.getTravelDistance();
            user.setKmPublicTransport(user.getKmPublicTransport() - distance);
        } else if (activityName.equals("bike")) {
            distance = activity.getTravelDistance();
            user.setKmCycled(user.getKmCycled() - distance);
        } else if (activityName.equals("pescetarian")) {
            user.setNumPescMeals(user.getNumPescMeals() - 1);
        } else if (activityName.equals("vegetarian")) {
            user.setNumVegMeals(user.getNumVegMeals() - 1);
        } else if (activityName.equals("vegan")) {
            user.setNumVeganMeals(user.getNumVeganMeals() - 1);
        } else if (activityName.equals("tree")) {
            user.setNumTreesPlanted(user.getNumTreesPlanted() - 1);
        } else if (activityName.equals("solar")) {
            user.setNumSolarPanels(user.getNumSolarPanels() - activity.getPanelSurface());
        }

        if (activity.getLocalMeals() > 0) {
            user.setNumLocalMeals(user.getNumLocalMeals() - 1);
        }

        // Always update the emission savings.
        user.setTotalEmissionSaved(user.getTotalEmissionSaved()
                - (int) Math.round(activity.getEmissionSaving()));
        user.setTotalWaterSaved(user.getTotalWaterSaved() - (int) Math.round(activity.getWaterSaving()));
        user.setTotalLandSaved(user.getTotalLandSaved() - (int) Math.round(activity.getLandSaving()));

        UserInterface.updateUser(user);
        setBadges(user);
    }

    /**
     * Compute the savings for the food, travel and home activity categories and
     * display them on a pie chart.
     * @param activities All the activities of the user.
     */
    private void computeSavings(ArrayList<Activity> activities) {

        carbonFood = 0;
        carbonTravel = 0;
        carbonHome = 0;

        if (activities != null) {
            for (Activity activity : activities) {
                if (food.contains(activity.getName())) {
                    carbonFood += (int) activity.getEmissionSaving();
                }

                if (travel.contains(activity.getName())) {
                    carbonTravel += (int) activity.getEmissionSaving();
                }

                if (home.contains(activity.getName())) {
                    carbonHome += (int) activity.getEmissionSaving();
                }
            }
        }

        pieChartData.get(0).setPieValue(carbonFood);
        pieChartData.get(1).setPieValue(carbonTravel);
        pieChartData.get(2).setPieValue(carbonHome);
    }

    /**
     * Sets the statistics of the given user.
     * @param user The user for which the statistics are to be shown.
     */
    private void showStats(User user) {
        statsCarbon.setText(user.getTotalEmissionSaved() + " kg");
        statsWater.setText(user.getTotalWaterSaved() + " L");
        statsLand.setText(user.getTotalLandSaved() + " m2");
        statsBicycle.setText(user.getKmCycled() + " km");
        statsTransport.setText(user.getKmPublicTransport() + " km");
        statsVegan.setText(Integer.toString(user.getNumVeganMeals()));
        statsVegetarian.setText(Integer.toString(user.getNumVegMeals()));
        statsLocal.setText(Integer.toString(user.getNumLocalMeals()));
        statsTrees.setText(Integer.toString(user.getNumTreesPlanted()));
        statsSolar.setText(Integer.toString(user.getNumSolarPanels()));
    }

    /**
     * Sets the opacity of the badges based on whether the user has earned an achievement.
     * @param user The user for which the badges are to be set.
     */
    private void setBadges(User user) {

        if (user.getNumTreesPlanted() >= 1) {
            b1.setOpacity(1);
        } else {
            b1.setOpacity(0.2);
        }

        if (user.getNumTreesPlanted() >= 10) {
            b2.setOpacity(1);
        } else {
            b2.setOpacity(0.2);
        }

        if (user.getNumTreesPlanted() >= 100) {
            b3.setOpacity(1);
        } else {
            b3.setOpacity(0.2);
        }

        if (user.getNumSolarPanels() >= 1) {
            b4.setOpacity(1);
        } else {
            b4.setOpacity(0.2);
        }

        if (user.getNumSolarPanels() >= 25) {
            b5.setOpacity(1);
        } else {
            b5.setOpacity(0.2);
        }

        if (user.getNumSolarPanels() >= 50) {
            b6.setOpacity(1);
        } else {
            b6.setOpacity(0.2);
        }
    }


    /**
     * Sends a simple request to the server.
     * @return Returns true if a connection to the server can be established.
     */
    private boolean testServer() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String fooResourceUrl = Url.url + "/hello";
            ResponseEntity<Message> response =
                    restTemplate.getForEntity(fooResourceUrl, Message.class);
            System.out.println(response.getBody());
            return true;
        } catch (ResourceAccessException e) {
            /*snackbar.enqueue(new JFXSnackbar.SnackbarEvent(noConnection));*/
            return false;
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        pieTest.setData(pieChartData);

        // Set up Google login page.
        googleLoginPage.setVisible(false);
        webEngine = googleLoginPage.getEngine();

        // Move panes accordingly.
        paneDashboard.toFront();
        paneLogin.toFront();


        // Set the profile picture.
        userImage.setFill(new ImagePattern(img));

        // Initialize snackbars.
        snackbar = new JFXSnackbar(stackSnackbar);

        // Set snackbar layout IDs.
        activityAdded.getStyleClass().add("greenSnackbar");
        activityAddException.getStyleClass().add("redSnackbar");
        activityRemoved.getStyleClass().add("greenSnackbar");
        connectionSuccess.getStyleClass().add("greenSnackbar");
        connectionFail.getStyleClass().add("redSnackbar");
        noConnection.getStyleClass().add("redSnackbar");
        newFriendRequest.getStyleClass().add("greenSnackbar");
        sentFriendRequest.getStyleClass().add("greenSnackbar");
        acceptFriendRequest.getStyleClass().add("greenSnackbar");

        snackbarAdded = new JFXSnackbar.SnackbarEvent(activityAdded);
        snackbarRemoved = new JFXSnackbar.SnackbarEvent(activityRemoved);

        // Set travel combo box and select the first item by default.
        travelBefore.getItems().addAll("gas car", "hybrid car", "electric car",
                "plane", "train", "bus", "bike");
        travelBefore.getSelectionModel().selectFirst();

        // When the history task completes, set the badges,
        // show the history list and show the total savings.
        // If the task does not complete, show an error message.
        historyTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent wse) {

                activities = historyTask.getValue();

                if (activities != null) {

                    // Display history and savings from the database.
                    showHistory();
                    // computeSavings();
                    showSavings();

                    // Notify the user of a successful connection.
                    snackbar.enqueue(new JFXSnackbar.SnackbarEvent(connectionSuccess));
                } else {

                    // Notify the user of an unsuccessful connection.
                    snackbar.enqueue(new JFXSnackbar.SnackbarEvent(connectionFail));
                }
            }
        });
    }
}
