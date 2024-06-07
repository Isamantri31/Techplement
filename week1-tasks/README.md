# Quiz Generator

A command-line quiz generator application in Java that allows users to create quizzes, add questions with multiple-choice options, specify correct answers, and take quizzes to test their knowledge.

## Features

- Create quizzes
- Add questions to quizzes with multiple-choice options
- Specify correct answers for the questions
- Take quizzes and get feedback on scores
- Delete quizzes
- Delete specific questions from quizzes

## Requirements

- Java Development Kit (JDK) 8 or higher
- Visual Studio Code (VS Code)

## Setup Instructions

### 1. Install JDK

Make sure you have Java Development Kit (JDK) installed on your system. You can download it from the [official Oracle website](https://www.oracle.com/java/technologies/javase-downloads.html) or use OpenJDK.

### 2. Install VS Code

Download and install Visual Studio Code from the [official website](https://code.visualstudio.com/).

### 3. Install Java Extension Pack for VS Code

Open VS Code and install the Java Extension Pack:

1. Go to the Extensions view by clicking the Extensions icon in the Activity Bar on the side of the window or pressing `Ctrl+Shift+X`.
2. Search for "Java Extension Pack".
3. Click "Install" on the "Java Extension Pack" by Microsoft.

### 4. Create a New Project Folder

Create a new folder for your project and open it in VS Code:

1. Open VS Code.
2. Click on "File" > "Open Folder..." and select the new folder.

### 5. Create the Java File

Create a new Java file in your project folder:

1. Right-click in the Explorer panel on the left and select "New File".
2. Name the file `QuizApp.java`.

### 6. Copy and Paste the Code

Copy the Java code from the project repository and paste it into `QuizApp.java`.

### 7. Running the Program

After copying the code into `QuizApp.java`, follow these steps to run the program:

1. **Open the Terminal**: Open the integrated terminal in VS Code by selecting `View` > `Terminal` from the top menu or pressing ``Ctrl+` ``.

2. **Compile the Java Program**: In the terminal, navigate to the directory containing `QuizApp.java` (if not already there) and compile the Java program by running:

    ```sh
    javac QuizApp.java
    ```

   This will compile your Java file and generate the necessary class files.

3. **Run the Program**: After successfully compiling the Java file, run the program by executing:

    ```sh
    java QuizApp
    ```

## Using the Application

Once the application is running, you can use the following commands as prompted by the application:

- `create`: Create a new quiz
  - Prompts for the title of the new quiz.
- `add`: Add a question to a quiz
  - Prompts for the quiz title, the question text, the number of choices, the choices themselves, and the correct answer.
- `take`: Take a quiz
  - Prompts for the quiz title and then allows the user to take the quiz, displaying the score at the end.
- `list`: List all available quizzes
- `delete-quiz`: Delete a quiz
  - Prompts for the quiz title to delete.
- `delete-question`: Delete a question from a quiz
  - Prompts for the quiz title and the index of the question to delete.
- `help`: Show the help message listing all commands.
- `exit`: Exit the application.

## Example Session

Here’s an example of what an interactive session might look like:

```sh
Welcome to the Quiz Generator!
Commands:
  create          - Create a new quiz
  add             - Add a question to a quiz
  take            - Take a quiz
  list            - List all quizzes
  delete-quiz     - Delete a quiz
  delete-question - Delete a question from a quiz
  help            - Show this help message
  exit            - Exit the application

Enter command: create
Enter quiz title: General Knowledge

Quiz 'General Knowledge' created.

Enter command: add
Enter quiz title: General Knowledge
Enter question: What is the capital of France?
Enter number of choices: 4
Enter choice 1: Paris
Enter choice 2: Berlin
Enter choice 3: Madrid
Enter choice 4: Rome
Enter correct answer number: 1

Question added to quiz 'General Knowledge'.

Enter command: take
Enter quiz title: General Knowledge

1. What is the capital of France?
   1. Paris
   2. Berlin
   3. Madrid
   4. Rome
Your answer: 1
Correct!

You scored 1 out of 1

Enter command: exit

Thank you for using the Quiz Generator. Goodbye!
