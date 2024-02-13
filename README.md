# QuizDroid 
An application that will allow users to take multiple-choice quizzes

## Basics
### Stories 
- As a user, when I start the app, I should see a list of different topics on which to take a quiz. (For now, these should be hard-coded to read "Math", "Physics", and "Marvel Super Heroes", as well as any additional topics you feel like adding into the mix.)
- As a user, when I select a topic from the list, it should take me to the "topic overview" page, which displays a brief description of this topic, the total number of questions in this topic, and a "Begin" button taking me to the first question.
- As a user, when I press the "Begin" button from the topic overview page, it should take me to the first question page, which will consist of a question (TextView), four radio buttons each of which consist of one answer, and a "Submit" button.
- As a user, when I am on a question page and I press the "Submit" button, if no radio button is selected, it should do nothing. If a radio button is checked, it should take me to the "answer" page. (Ideally, the Submit button should not be visible until a radio button is selected.)
- As a user, when I am on the "answer" page, it should display the answer I gave, the correct answer, and tell me the total number of correct vs incorrect answers so far ("You have 5 out of 9 correct"), and display a "Next" button taking me to the next question page, or else display a "Finish" button if that is the last question in the topic.
- As a user, when I am on the "answer" page, and it is the last question of the topic, the "Finish" button should take me back to the first topic-list page, so that I can take a new quiz.

### Grading
  - repo should be called 'quizdroid'; do work on branch 'basicui'


## Extra Credit 
- [x] As a user on the question page, if I hit the "back" button it should NOT take me to the answer summary page of the previous question, but to the previous question page directly.

- [x] As a user on the first question page of a topic, hitting "back" should take me back to the topic list page, not the topic overview page.


## Domain Models/Repository
An application that will allow users to take multiple-choice quizzes
  now we will refactor to use a domain model and an Application object
  note that a future version of this codebase will require permissions to be set; this can be done now or later, as you wish

### Developer Tasks
- Create a class called QuizApp extending android.app.Application and make sure it is referenced from the app manifest; override the onCreate() method to emit a message to the diagnostic log to ensure it is being loaded and run
- Use the "Repository" pattern to create a TopicRepository interface; create one implementation that simply stores elements in memory from a hard-coded list initialized on startup.
  - Create domain objects for Topic and Question
    - a Question is question text, four answers, and an integer saying which of the four answers is correct
    - a Topic is a title, short description, long description, and a collection of Question objects
- Provide a method or property on QuizApp for accessing the TopicRepository.
- Refactor the activities in the application to use the TopicRepository.
  - On the topic list page, the title and the short description should come from the similar fields in the Topic object.
  - On the topic overview page, the title and long description should come from the similar fields in the Topic object. The Question object should be similarly easy to match up to the UI.
 
### Grading
- repo should be called 'quizdroid' on branch 'repository'
- QuizApp extends Application, holds TopicRepository instance, is referenced from manifest, and writes to log
- TopicRepository provides all access to the Topic/Quiz objects, and all data is coming from those objects

### Extra Credit
- [ ] In the next part, we will need this application to need to access the Internet, among other things. Look through the list of permissions in the Android documentation, and add uses-permission elements as necessary to enable that now.
- [ ] Create some unit tests that test the TopicRepository
- [ ] Refactor the domain model so that Topics can have an icon to go along with the title and descriptions. (Use the stock Android icon for now if you don't want to test your drawing skills.) Refactor the topic list view to use the icon as part of the layout for each item in the list view. Display the icon on the topic overview page.
