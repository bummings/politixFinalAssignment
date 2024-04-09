// Edd Burke
// CompSci311: Artificial Intelligence
// Assignment 2: AI Survey Program
// 04/09/2024

// This program will ask the user a survey consisting of 20 questions. The answers will be accepted, talleyed internally and checked for a majority political ideology consistent with the answers and how they align with a given political ideology. 

// These questions of course can be modified, or added / removed. Throughout various parts of the survey, that is to say prior to its conclusion, the program will try to make an educated guess of the user's political ideology. At the end, the prediction is updated for the final time and displayed one more time to the user. 

// The questions and their answers are written one at a time to their own respective category text files within the program directory. For example: if the user selects an answer that is aligned with a "moderate left" weight, that question and answer are written to the file "moderate_left.txt". If there are no answers aligning to that category by the end of the survey (ex: no answer aligns with "hard right" ideology), the text file will not be created.

// Ideally, I would have broken this program into multiple components to avoid a main executable well under it's current length of almost 300 lines. However, given the instructions on the Study.com website for this project, it was not clear that this was allowed and that the entire functionality must opearate in one single executable, hence this "politix.java" main method encapsulates everything required.

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;


public class politix {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    
    // question list
    String[] questions = {
      "1. What is the best way to address climate change?",
      "2. What is your view on social welfare programs?",
      "3. What is your stance on immigration?",
      "4. What should be done about gun control?",
      "5. What is the best approach to foreign policy?",
      "6. What is your view on the minimum wage?",
      "7. How should healthcare be reformed?",
      "8. What is the role of government in education?",
      "9. How should the government address income inequality?",
      "10. What approach should be taken to address national debt?",
      "11. What is the best strategy for economic growth?",
      "12. What is your stance on labor unions?",
      "13. What is your view on abortion?",
      "14. How should the tax system be reformed?",
      "15. What should be the government's role in the economy?",
      "16. How should the government address the housing crisis?",
      "17. What approach should be taken towards environmental regulation?",
      "18. What is the best way to deal with crime?",
      "19. How should the nation's infrastructure be updated and maintained?",
      "20. What political party do you affiliate most with?"
    };

    // this is a 2 dimensional aray, first index for the question, second for the individual answers
    String[][] answers = {
      {
        "A. Implement a global carbon tax and invest heavily in renewable energy.",
        "B. Oppose government intervention; favor market-based solutions.",
        "C. Subsidize clean energy and set achievable emission targets.",
        "D. Offer tax incentives for businesses that reduce carbon emissions."
      },
      {
        "A. Support targeted welfare programs with some conditions.",
        "B. Minimize welfare programs in favor of private charity.",
        "C. Reform welfare to encourage work and reduce dependency.",
        "D. Expand social welfare programs to provide for all basic needs."
      },
      {
        "A. Increase funding for public schools and teachers' salaries.",
        "B. Support school choice, including charter schools and vouchers.",
        "C. Minimize federal control; advocate for local decision-making.",
        "D. Free college education for all."
      },
      {
        "A. Ban all assault weapons and implement strict gun control laws.",
        "B. Oppose any additional gun control measures.",
        "C. Implement universal background checks and red flag laws.",
        "D. Protect the Second Amendment but support limited restrictions."
      },
      {
        "A. Pro-choice but with some limits after the first trimester.",
        "B. Support unrestricted access to abortion at any stage.",
        "C. Support making abortion illegal in all or most cases.",
        "D. Allow abortions in limited cases; support some restrictions."
      },
      {
        "A. Implement wealth taxes and redistribute wealth to the poor.",
        "B. Create opportunities for upward mobility through education and job training.",
        "C. Increase the minimum wage and support social welfare programs.",
        "D. Encourage free-market policies to stimulate economic growth."
      },
      {
        "A. Reduce government involvement; advocate for free-market healthcare.",
        "B. Establish a single-payer healthcare system.",
        "C. Promote competition among private insurers to lower costs.",
        "D. Expand public healthcare options but allow private insurance."
      },
      {
        "A. Strengthen legal immigration processes and secure the borders.",
        "B. Implement strict immigration controls and prioritize national security.",
        "C. Advocate for open borders and increased refugee intake.",
        "D. Support a path to citizenship for undocumented immigrants." 
      },
      {
        "A. Oppose raising the minimum wage; market forces should determine wages.",
        "B. Adjust the minimum wage carefully to avoid negative impacts on businesses.",
        "C. Significantly increase the minimum wage to a living wage.",
        "D. Raise the minimum wage to keep up with inflation and cost of living."
      },
      {
        "A. Prioritize spending cuts and economic growth to reduce debt.",
        "B. Implement significant spending cuts to rapidly reduce national debt.",
        "C. Balance the budget through a mix of spending cuts and tax increases.",
        "D. Accept higher debt levels to fund social programs and infrastructure."
      },
      {
        "A. Support a mix of government spending and private-sector incentives.",
        "B. Invest in public infrastructure and services to stimulate the economy.",
        "C. Implement broad tax cuts and deregulation to boost the economy.",
        "D. Encourage entrepreneurship and reduce government spending."
      },
      {
        "A. Strongly support labor unions and the right to collective bargaining.",
        "B. Recognize the role of unions but advocate for minimal restrictions on businesses.",
        "C. Favor limiting the power of labor unions to promote business flexibility.",
        "D. Support unions but balance their interests with those of businesses."
      },
      {
        "A. Support alliances but be cautious with military intervention.",
        "B. Maintain a strong military presence and put national interests first.",
        "C. Favor unilateral action and a strong military to counter global threats.",
        "D. Prioritize diplomacy and international cooperation over military action."
      },
      {
        "A. Advocate for a flat tax rate to simplify the tax code.",
        "B. Reduce taxes across the board, especially for businesses.",
        "C. Implement a progressive tax system where the wealthy pay more.",
        "D. Support fair taxes but avoid excessive burdens on the wealthy."
      },
      {
        "A. The government should play a major role in controlling the economy to ensure equity.",
        "B. The government should mainly provide a stable environment for economic growth.",
        "C. Minimize government intervention; the free market should dictate the economy.",
        "D. Government intervention is necessary in key sectors to protect public interests."
      },
      {
        "A. Provide subsidies for low-income families and support affordable housing projects.",
        "B. Offer tax incentives for developers to build more housing.",
        "C. Deregulate the housing market to increase supply.",
        "D. Build public housing and control rents to ensure affordability."
      },
      {
        "A. Enact stringent regulations on industries to protect the environment.",
        "B. Reduce regulatory burdens on businesses to promote innovation.",
        "C. Balance environmental protection with economic growth.",
        "D. Prioritize economic growth over environmental regulations."
      },
      {
        "A. Support law enforcement and the judicial system to reduce crime.",
        "B. Combine community policing with social programs.",
        "C. Advocate for strict law enforcement and harsh penalties for criminals.",
        "D. Focus on rehabilitation and address the root causes of crime."
      },
      {
        "A. Massively increase public spending on green and sustainable infrastructure.",
        "B. Public-private partnerships to update infrastructure efficiently.",
        "C. Reduce government involvement; let the private sector lead.",
        "D. Invest in infrastructure with a focus on public transport and renewable energy."
      },
      {
        "A. Democrat",
        "B. Republican",
        "C. Green",
        "D. Libertarian"
      }
    };

    // the ideology for a given answer
    String[][] answerIdeologies = {
      {"Hard Left", "Hard Right", "Moderate Left", "Moderate Right"},
      {"Moderate Left", "Hard Right", "Moderate Right", "Hard Left"},
      {"Moderate Right", "Hard Right", "Hard Left", "Moderate Left"},
      {"Hard Left", "Hard Right", "Moderate Left", "Moderate Right"},
      {"Moderate Left", "Moderate Right", "Hard Right", "Hard Left"},
      {"Hard Right", "Moderate Right", "Hard Left", "Moderate Left"},
      {"Hard Right", "Hard Left", "Moderate Right", "Moderate Left"},
      {"Moderate Left", "Moderate Right", "Hard Right", "Hard Left"},
      {"Hard Left", "Moderate Right", "Moderate Left", "Hard Right"},
      {"Moderate Right", "Hard Right", "Moderate Left", "Hard Left"},
      {"Moderate Left", "Hard Left", "Hard Right", "Moderate Right"},
      {"Hard Left", "Moderate Right", "Hard Right", "Moderate Left"},
      {"Moderate Left", "Hard Left", "Hard Right", "Moderate Right"},
      {"Moderate Right", "Hard Right", "Hard Left", "Moderate Left"},
      {"Hard Left", "Moderate Right", "Hard Right", "Moderate Left"},
      {"Moderate Left", "Moderate Right", "Hard Right", "Hard Left"},
      {"Hard Left", "Moderate Right", "Moderate Left", "Hard Right"},
      {"Moderate Right", "Moderate Left", "Hard Right", "Hard Left"},
      {"Hard Left", "Moderate Right", "Hard Right", "Moderate Left"},
      {"Hard Left", "Moderate Left", "Moderate Right", "Hard Right"}
    };


   // for loop begins over question set
    for (int i = 0; i < questions.length; i++) {
      System.out.println(questions[i]);  // ask question
    
      // find the answer set matching the question with the same index number
      for (String answer : answers[i]) { 
        System.out.println(answer); // print answer set
      }
    
      // ask for answer
      System.out.println("\nPlease choose an answer: ");
      String userInput = scanner.nextLine().toUpperCase();
      int currentQuestionIndex = i;
      int currentAnswerIndex = currentQuestionIndex;
      
      // while loop to circle until valid input is entered
      while (!(userInput.equals("A") || userInput.equals("B") || userInput.equals("C") || userInput.equals("D"))) {
        System.out.println("Invalid input. Please enter your choice as A, B, C or D: ");
        userInput = scanner.nextLine().toUpperCase();
      }
      // successful input, question loop can continue

      // userChoiceIndex is the result of the input letter's character code (65, 66, 67, 68), subtract 65 to ensure a value between 0 and 3 (A through D). This is so that we can match that to the index value of the answersIdeologies array
      int userChoiceIndex = userInput.charAt(0) - 65; 
      System.out.println("You selected: "+ userInput + "\n\n\n");

      String sentiment = answerIdeologies[currentQuestionIndex][userChoiceIndex];

      // initialize blank filename variable prior to FileWriter setup
      String fileName = "";

      // Initialize an array to store the count of each sentiment
      int[] sentimentCounts = new int[4];

      // switch statement to check for which sentiment is associated with the answer
      switch (sentiment) {
        case "Hard Left":
          fileName = "Hard_Left.txt";
          sentimentCounts[0]++;
          break;
        
        case "Moderate Left":
          fileName = "Moderate_Left.txt";
          sentimentCounts[1]++;
          break;
        
        case "Moderate Right":
          fileName = "Moderate_Right.txt";
          sentimentCounts[2]++;
          break;
        
        case "Hard Right":
          fileName = "Hard_Right.txt";
          sentimentCounts[3]++;
          break;
        
      }

      // write question and answer to respective sentiment text file
      try (FileWriter writer = new FileWriter(fileName, true)) {
        writer.write("Question: " + questions[currentQuestionIndex] + "\n");
        writer.write("Answer: " + answers[currentQuestionIndex][userChoiceIndex] + "\n\n");
        // System.out.println("Question and answer written to " + fileName);
      } 
      catch (IOException e) {
          System.out.println("An error occurred while writing to the file: " + e.getMessage());
      }

      //setup for choice weighting for predictions
      int totalCount = sentimentCounts[0] + sentimentCounts[1] + sentimentCounts[2] + sentimentCounts[3];
      double majorityThreshold = 0.7; // 70% threshold for majority prior to prediction
      String majorityCategory = "";  // initialize majority variable to ensure access conditional
      
      // Check the sentiment counts after a specific number of questions (e.g., 14)
      if (currentQuestionIndex == 9 || currentQuestionIndex == 13 || currentQuestionIndex == 16 || currentQuestionIndex == 19) { // Index 9, 13, 16 represents questions 8, 12, 15, 20

        if (sentimentCounts[0] >= totalCount * majorityThreshold) {
            majorityCategory = "Hard Left";
        } else if (sentimentCounts[1] >= totalCount * majorityThreshold) {
            majorityCategory = "Moderate Left";
        } else if (sentimentCounts[2] >= totalCount * majorityThreshold) {
            majorityCategory = "Moderate Right";
        } else if (sentimentCounts[3] >= totalCount * majorityThreshold) {
            majorityCategory = "Hard Right";
        }

        if (!majorityCategory.isEmpty()) {
          System.out.println("\n\n\n⚠️ ⚠️ ⚠️ As of now, I predict the user's political ideology aligns more with: " + majorityCategory + "⚠️ ⚠️ ⚠️\n\n");
        } else {
            System.out.println("As of right now, there is no clear prediction.");
        }
      }

    // terxminate survey at the end if the question array
    if (currentQuestionIndex == 19) {
        System.out.println("We have predicted your political ideology to align most with: " + majorityCategory );
        // Exit the loop when all questions have been asked
        break; 
      }
    }   
    scanner.close();
  }
}

