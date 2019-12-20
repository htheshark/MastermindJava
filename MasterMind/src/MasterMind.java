import java.util.Scanner;

public class MasterMind {
    static int[] userNum1 = new int[9]; //correct place
    static int[] userNum2 = new int[9]; //wrong place, right number
    public static void main(String[] args) {
        //beginning:
        int[] possibleNumber = new int[5]; //0 = need to check, 1 = don't use, 2 = do use
        int slot1 = 0, slot2 = 0, slot3 = 0, slot4 = 0;
        int[] notSlot1 = new int[9];
        int[] notSlot2 = new int[10];
        int[] notSlot3 = new int[9];
        int[] notSlot4 = new int[9];
        int[] code = new int[4]; //change to code1[guessnum], code2[], etc.
        int guessNum = 1;
        boolean[] itWasThisSlot = new boolean[3];
        Scanner scnr = new Scanner(System.in);
        for (int x = 0; x <= 5; x++){
            possibleNumber[x] = 0;
        }
        String guess = "1 2 3 4";
        getUserInput(scnr, guess, guessNum);
        if (userNum1[0] + userNum2[0] < 4) {
            if (userNum1[0] + userNum2[0] == 0) {
                for (int x = 0; x <= 3; x++) {
                    possibleNumber[x] = 1; //don't use 1 - 4
                }
            } else if (userNum1[0] == 0) {
                notSlot1[0] = 1;
                notSlot2[0] = 2;
                notSlot3[0] = 3;
                notSlot4[0] = 4;
            }
        } else if (userNum1[0] == 4) {
            endRound(scnr, guessNum);
        } else { //this is when userNum1 + userNum2 = 4
            possibleNumber[4] = 1; //don't use 5 - 6
            possibleNumber[5] = 1;
            for (int x = 0; x <= 3; x++) {
                possibleNumber[x] = 2; //do use 1 - 4
            }
            if (userNum2[0] == 4) {
                notSlot1[0] = 1;
                notSlot2[0] = 2;
                notSlot3[0] = 3;
                notSlot4[0] = 4;
            }
        }
        while (userNum1[guessNum - 1] != 4) {
            int dontUse = 0, doUse = 0;
            for (int x = 0; x <= 5; x++){
                if (possibleNumber[x] == 1){
                    dontUse++;
                }else if(possibleNumber[x] == 2){
                    doUse++;
                }
            }
            if ((dontUse == 0) && (doUse != 4)) {
                guessNum++;
                guess = "3 4 5 6";
                getUserInput(scnr, guess, guessNum);
                if ((userNum1[1] + userNum2[1] < 4) && (userNum1[1] + userNum2[1] != 0)){
                    if (userNum1[0] + userNum2[0] > userNum1[1] + userNum2[1]) { //if first guess was more accurate than second guess
                        while (dontUse == 0) {
                            int counter = 0;
                            int checkForUse = 6 - counter;
                            guessNum++;
                            guess = checkForUse + " " + checkForUse + " " + checkForUse + " " + checkForUse;
                            getUserInput(scnr, guess, guessNum);
                            if (userNum1[guessNum - 1] + userNum2[guessNum - 1] == 0) {
                                possibleNumber[checkForUse] = 1; //checked number is dont use now
                                dontUse++;
                                break;
                            }else{
                                possibleNumber[checkForUse] = 2; //checked number is do use now
                                doUse++;
                            }
                        }
                    } else {
                        while (dontUse == 0) {
                            int counter = 0;
                            int checkForUse = 1 + counter;
                            guessNum++;
                            guess = checkForUse + " " + checkForUse + " " + checkForUse + " " + checkForUse;
                            getUserInput(scnr, guess, guessNum);
                            if (userNum1[guessNum - 1] + userNum2[guessNum - 1] == 0) {
                                possibleNumber[checkForUse] = 1; //checked number is dont use now
                                dontUse++;
                                break;
                            }else{
                                possibleNumber[checkForUse] = 2; //checked number is do use now
                                doUse++;
                            }
                        }
                    }
                }else if (userNum1[1] + userNum2[1] == 0) {
                    int notThis = 3; //3,4,5,6 can't be used
                    for (int x = 0; x < 4; x++) {
                        possibleNumber[notThis] = 1;
                        dontUse++;
                        notThis++;
                    }
                }
            } else if (doUse != 0) {
                while ((userNum1[guessNum - 1] != 4) && (userNum2[guessNum - 1] != 4)) {
                    guessNum++;
                    createGuess(possibleNumber, slot1, slot2, slot3, slot4, notSlot1, notSlot2, notSlot3, notSlot4, code);
                    getUserInput(scnr, guess, guessNum);
                    if (userNum1[guessNum - 1] == 4) {
                        endRound(scnr, guessNum);
                    } else if (userNum2[guessNum - 1] == 4) {
                        notSlot1[guessNum - 1] = code[0];
                        notSlot2[guessNum - 1] = code[1];
                        notSlot3[guessNum - 1] = code[2];
                        notSlot4[guessNum - 1] = code[3];
                        slot1 = 0;
                        slot2 = 0;
                        slot3 = 0;
                        slot4 = 0;
                    } else if (userNum1[guessNum - 1] > guessNum - 1) {
                        switch (guessNum) {
                            case 2:
                                slot1 = code[0];
                                break;
                            case 3:
                                slot2 = code[1];
                                break;
                            case 4:
                                slot3 = code[2];
                                break;
                            case 5:
                                slot4 = code[3];
                                break;
                        }
                    } else if (userNum1[guessNum - 1] == 0) {
                        notSlot1[guessNum - 1] = code[0];
                        notSlot2[guessNum - 1] = code[1];
                        notSlot3[guessNum - 1] = code[2];
                        notSlot4[guessNum - 1] = code[3];
                    } else if ((userNum1[guessNum - 1] == 1) && (slot1 + slot2 + slot3 + slot4 == 0)) {
                        if (possibleNumber[code[0]] != 2) {
                            slot1 = code[0];
                        }
                        if (possibleNumber[code[1]] != 2) {
                            slot2 = code[1];
                        }
                        if (possibleNumber[code[2]] != 2) {
                            slot3 = code[2];
                        }
                        if (possibleNumber[code[3]] != 2) {
                            slot4 = code[3];
                        }
                    }
                }
            }else{ //when there are no definitely do use, but there are definitely dont use

            }
        }
        //this might replace endGuesses, unknown until it is finished
        //createGuess(definitelyDontUse, definitelyDoUse, slot1, slot2, slot3, slot4, notSlot1, notSlot2, notSlot3, notSlot4, code);

        //not where this will be in code, these are basically final guesses.
        //endGuesses(definitelyDoUse, slot1, slot2, slot3, slot4, notSlot1, notSlot2, notSlot3, notSlot4, code);
    }

    public static void guessWithNoDo(int[] definitelyDontUse, int slot1, int slot2, int slot3, int slot4, int[] notSlot1, int[] notSlot2, int[] notSlot3, int[] notSlot4, int[] code){
        String guess;

    }

    public static void createGuess(int[] possibleNumber, int slot1, int slot2, int slot3, int slot4, int[] notSlot1, int[] notSlot2, int[] notSlot3, int[] notSlot4, int[] code) {
        String guess;
        boolean canUse =  true;
        int x = 0;
        for (int counter = 0; counter > 3; counter++){
            code[counter] = 0;
        }
        while (code[0] == 0 || x < 4){
            switch (slot1){
                case 0:
                    for (int y = 0; y <= 5; y++){
                        if (possibleNumber[y] == 2)  {
                            for (int z = 0; z <= 9; z++) {
                                if (notSlot1[z] == y) {
                                    canUse = false;
                                    break;
                                }
                            }
                        }
                        if (canUse){
                            code[0] = y;
                            break;
                        }else {
                            canUse = true;
                        }
                    }
                    break;
                case 1: code[0] = 1;
                    break;
                    case 2:
                    code[0] = 2;
                    break;
                case 3:
                    code[0] = 3;
                    break;
                case 4:
                    code[0] = 4;
                    break;
            }
            x++;
        }
        x = 0;
        if (slot1 != 0) { //if the slot is definitely filled
            while (code[1] == 0 || x < 4) {
                switch (slot2) {
                    case 0:
                        for (int y = 0; y <= 5; y++){
                            if (possibleNumber[y] == 2)  {
                                for (int z = 0; z <= 9; z++) {
                                    if (notSlot1[z] == y) {
                                        canUse = false;
                                        break;
                                    }
                                }
                            }
                            if (canUse){
                                code[1] = y;
                                break;
                            }else {
                                canUse = true;
                            }
                        }
                        break;
                    case 1:
                        code[1] = 1;
                        break;
                    case 2:
                        code[1] = 2;
                        break;
                    case 3:
                        code[1] = 3;
                        break;
                    case 4:
                        code[1] = 4;
                        break;
                }
                x++;
            }
            x = 0;
            if (slot2 != 0){
                while (code[2] == 0 || x < 4){
                    switch (slot3) {
                        case 0:
                            for (int y = 0; y <= 5; y++){
                                if (possibleNumber[y] == 2)  {
                                    for (int z = 0; z <= 9; z++) {
                                        if (notSlot1[z] == y) {
                                            canUse = false;
                                            break;
                                        }
                                    }
                                }
                                if (canUse){
                                    code[2] = y;
                                    break;
                                }else {
                                    canUse = true;
                                }
                            }
                            break;
                        case 1:
                            code[2] = 1;
                            break;
                        case 2:
                            code[2] = 2;
                            break;
                        case 3:
                            code[2] = 3;
                            break;
                        case 4:
                            code[2] = 4;
                            break;
                    }
                    x++;
                }
                x = 0;
                if (slot3 != 0){
                    while (code[3] == 0 || x < 4){
                        switch (slot4) {
                            case 0:
                                for (int y = 0; y <= 5; y++){
                                    if (possibleNumber[y] == 2)  {
                                        for (int z = 0; z <= 9; z++) {
                                            if (notSlot1[z] == y) {
                                                canUse = false;
                                                break;
                                            }
                                        }
                                    }
                                    if (canUse){
                                        code[3] = y;
                                        break;
                                    }else {
                                        canUse = true;
                                    }
                                }
                                break;
                            case 1:
                                code[3] = 1;
                                break;
                            case 2:
                                code[3] = 2;
                                break;
                            case 3:
                                code[3] = 3;
                                break;
                            case 4:
                                code[3] = 4;
                                break;
                        }
                        x++;
                    }
                } else{
                    for (int loop = 0; loop <=5; loop ++) {
                        if (possibleNumber[loop] == 1){
                            code[3] = possibleNumber[loop];
                        }
                    }
                }
            } else{
                for (int counter = 2; counter <= 3; counter++) {
                    for (int loop = 0; loop <=5; loop ++) {
                        if (possibleNumber[loop] == 1){
                            code[counter] = possibleNumber[loop];
                        }
                    }
                }
            }
        } else{
            for (int counter = 1; counter <= 3; counter++) {
                for (int loop = 0; loop <=5; loop ++) {
                    if (possibleNumber[loop] == 1){
                        code[counter] = possibleNumber[loop];
                    }
                }
            }
        }
        guess = code[0] + " " + code[1] + " " + code[2] + " " + code[3];
    }

    public String guessFromDont(int[] possibleNumber, int slot1, int slot2, int slot3, int slot4, int[] notSlot1, int[] notSlot2, int[] notSlot3, int[] notSlot4, int[] code) {
        String guess;
        boolean canUse = true;
        int x = 0;
        switch (slot1) {
            case 0:

                break;
            case 1:
                code[0] = 1;
                break;
            case 2:
                code[0] = 2;
                break;
            case 3:
                code[0] = 3;
                break;
            case 4:
                code[0] = 4;
                break;
        }
        guess = code[0] + " " + code[1] + " " + code[2] + " " + code[3];
        return guess;
    }

    public static void endRound(Scanner scnr, int guessNum) {
        String yesNo;
        System.out.println("Code was guessed in " + guessNum + " turns. Play again?");
        yesNo = scnr.nextLine();
        if (yesNo.equalsIgnoreCase("no")) {
            //end
        } else {
            //start over
        }
    }

    public static void getUserInput(Scanner scnr, String guess, int guessNum) {
        System.out.println(guess);
        System.out.println("Numbers in the correct place:");
        userNum1[guessNum - 1] = scnr.nextInt();
        System.out.println("Correct numbers in the wrong place:");
        userNum2[guessNum - 1] = scnr.nextInt();

        verifyInput(userNum1, userNum2, guessNum);

    }

    public  static void verifyInput(int[] userNum1, int[] userNum2, int guessNum) {
        if (userNum1[guessNum - 1] + userNum2[guessNum - 1] > 4) {
            System.out.println("Numbers cant add to more than 4 FIX");
        }
    }
}


