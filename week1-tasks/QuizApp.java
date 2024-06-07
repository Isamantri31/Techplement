import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Question {
    private String questionText;
    private String[] choices;
    private int correctAnswerIndex;

    public Question(String questionText, String[] choices, int correctAnswerIndex) {
        this.questionText = questionText;
        this.choices = choices;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getChoices() {
        return choices;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

class Quiz {
    private String title;
    private List<Question> questions;

    public Quiz(String title) {
        this.title = title;
        this.questions = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }
}

class QuizManager {
    private Map<String, Quiz> quizzes;

    public QuizManager() {
        quizzes = new HashMap<>();
    }

    public void createQuiz(String title) {
        quizzes.put(title, new Quiz(title));
    }

    public Quiz getQuiz(String title) {
        return quizzes.get(title);
    }

    public void addQuestionToQuiz(String quizTitle, Question question) {
        Quiz quiz = quizzes.get(quizTitle);
        if (quiz != null) {
            quiz.addQuestion(question);
        } else {
            System.out.println("Quiz not found.");
        }
    }

    public void deleteQuiz(String title) {
        if (quizzes.remove(title) != null) {
            System.out.println("Quiz '" + title + "' deleted.");
        } else {
            System.out.println("Quiz not found.");
        }
    }

    public void deleteQuestionFromQuiz(String quizTitle, int questionIndex) {
        Quiz quiz = quizzes.get(quizTitle);
        if (quiz != null) {
            if (questionIndex >= 0 && questionIndex < quiz.getQuestions().size()) {
                quiz.getQuestions().remove(questionIndex);
                System.out.println("Question deleted from quiz '" + quizTitle + "'.");
            } else {
                System.out.println("Question index out of range.");
            }
        } else {
            System.out.println("Quiz not found.");
        }
    }

    public void listQuizzes() {
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available.");
        } else {
            System.out.println("Available quizzes:");
            for (String title : quizzes.keySet()) {
                System.out.println("- " + title);
            }
        }
    }
}

class QuizTaker {
    private Scanner scanner = new Scanner(System.in);

    public void takeQuiz(Quiz quiz) {
        List<Question> questions = quiz.getQuestions();
        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println((i + 1) + ". " + question.getQuestionText());
            String[] choices = question.getChoices();
            for (int j = 0; j < choices.length; j++) {
                System.out.println("   " + (j + 1) + ". " + choices[j]);
            }

            System.out.print("Your answer: ");
            int answer = scanner.nextInt() - 1;

            if (answer == question.getCorrectAnswerIndex()) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect! The correct answer was: " + choices[question.getCorrectAnswerIndex()]);
            }
        }

        System.out.println("\nYou scored " + score + " out of " + questions.size());
    }
}

public class QuizApp {
    private static QuizManager quizManager = new QuizManager();
    private static QuizTaker quizTaker = new QuizTaker();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        
        System.out.println("Welcome to the Quiz Generator!");
        printHelp();

        while (running) {
            System.out.print("\nEnter command: ");
            String command = scanner.nextLine().trim();

            switch (command.toLowerCase()) {
                case "create":
                    createQuiz();
                    break;
                case "add":
                    addQuestion();
                    break;
                case "take":
                    takeQuiz();
                    break;
                case "list":
                    quizManager.listQuizzes();
                    break;
                case "delete-quiz":
                    deleteQuiz();
                    break;
                case "delete-question":
                    deleteQuestion();
                    break;
                case "help":
                    printHelp();
                    break;
                case "exit":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid command. Type 'help' to see the list of commands.");
                    break;
            }
        }

        System.out.println("Thank you for using the Quiz Generator. Goodbye!");
    }

    private static void createQuiz() {
        System.out.print("Enter quiz title: ");
        String title = scanner.nextLine().trim();
        quizManager.createQuiz(title);
        System.out.println("Quiz '" + title + "' created.");
    }

    private static void addQuestion() {
        System.out.print("Enter quiz title: ");
        String quizTitle = scanner.nextLine().trim();
        Quiz quiz = quizManager.getQuiz(quizTitle);

        if (quiz == null) {
            System.out.println("Quiz not found.");
            return;
        }

        System.out.print("Enter question: ");
        String questionText = scanner.nextLine().trim();

        System.out.print("Enter number of choices: ");
        int numberOfChoices = scanner.nextInt();
        scanner.nextLine(); // consume the newline

        String[] choices = new String[numberOfChoices];
        for (int i = 0; i < numberOfChoices; i++) {
            System.out.print("Enter choice " + (i + 1) + ": ");
            choices[i] = scanner.nextLine().trim();
        }

        System.out.print("Enter correct answer number: ");
        int correctAnswerIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // consume the newline

        Question question = new Question(questionText, choices, correctAnswerIndex);
        quizManager.addQuestionToQuiz(quizTitle, question);
        System.out.println("Question added to quiz '" + quizTitle + "'.");
    }

    private static void takeQuiz() {
        System.out.print("Enter quiz title: ");
        String quizTitle = scanner.nextLine().trim();
        Quiz quiz = quizManager.getQuiz(quizTitle);

        if (quiz == null) {
            System.out.println("Quiz not found.");
            return;
        }

        quizTaker.takeQuiz(quiz);
    }

    private static void deleteQuiz() {
        System.out.print("Enter quiz title: ");
        String title = scanner.nextLine().trim();
        quizManager.deleteQuiz(title);
    }

    private static void deleteQuestion() {
        System.out.print("Enter quiz title: ");
        String quizTitle = scanner.nextLine().trim();
        System.out.print("Enter question index to delete: ");
        int questionIndex = scanner.nextInt();
        scanner.nextLine(); // consume the newline
        quizManager.deleteQuestionFromQuiz(quizTitle, questionIndex);
    }

    private static void printHelp() {
        System.out.println("Commands:");
        System.out.println("  create          - Create a new quiz");
        System.out.println("  add             - Add a question to a quiz");
        System.out.println("  take            - Take a quiz");
        System.out.println("  list            - List all quizzes");
        System.out.println("  delete-quiz     - Delete a quiz");
        System.out.println("  delete-question - Delete a question from a quiz");
        System.out.println("  help            - Show this help message");
        System.out.println("  exit            - Exit the application");
    }
}