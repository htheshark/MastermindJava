import java.util.Scanner;

public class MasterMind {
    static int[] userNum1 = new int[9];
    static int[] userNum2 = new int[9];
    public static void main(String[] args) {
        //beginning:
        int[] definitelyDontUse = new int[10];
        int[] definitelyDoUse = new int[10];
        int slot1 = 0, slot2 = 0, slot3 = 0, slot4 = 0;
        int[] notSlot1 = new int[9];
        int[] notSlot2 = new int[10];
        int[] notSlot3 = new int[9];
        int[] notSlot4 = new int[9];
        int[] code = new int[4];
        int guessNum = 1;
        boolean[] itWasThisSlot = new boolean[3];
        Scanner scnr = new Scanner(System.in);

        String guess = "1 2 3 4";
        getUserInput(scnr, guess, guessNum);
        if (userNum1[0] + userNum2[0] < 4) {
            if (userNum1[0] + userNum2[0] == 0) {
                definitelyDontUse[0] = 1;
                definitelyDontUse[1] = 2;
                definitelyDontUse[2] = 3;
                definitelyDontUse[3] = 4;
                definitelyDoUse[0] = 0;
            } else if (userNum1[0] == 0) {
                notSlot1[0] = 1;
                notSlot2[0] = 2;
                notSlot3[0] = 3;
                notSlot4[0] = 4;
                definitelyDoUse[0] = 0;
            }else{
                definitelyDontUse[0] = 0;
                definitelyDoUse[0] = 0;
            }
        } else if (userNum1[0] == 4) {
            endRound(scnr, guessNum);
        } else { //this is when userNum1 + userNum2 = 4
            definitelyDontUse[0] = 5;
            definitelyDontUse[1] = 6;
            for (int x = 0; x <= 3; x++) {
                definitelyDoUse[x] = x + 1;
            }
            if (userNum2[0] == 4) {
                notSlot1[0] = 1;
                notSlot2[0] = 2;
                notSlot3[0] = 3;
                notSlot4[0] = 4;
            }
        }
        while (userNum1[guessNum - 1] != 4) {
            if ((definitelyDontUse[0] == 0) && (definitelyDoUse[0] == 0)) {
                guessNum++;
                guess = "3 4 5 6";
                getUserInput(scnr, guess, guessNum);
                if ((userNum1[1] + userNum2[1] < 4) && (userNum1[1] + userNum2[1] != 0)){
                    if (userNum1[0] + userNum2[0] > userNum1[1] + userNum2[1]) { //if first guess was more accurate than second guess
                        while (definitelyDontUse[0] == 0) {
                            int counter = 0;
                            int checkForUse = 6 - counter;
                            guessNum++;
                            guess = checkForUse + " " + checkForUse + " " + checkForUse + " " + checkForUse;
                            getUserInput(scnr, guess, guessNum);
                            if (userNum1[guessNum - 1] + userNum2[guessNum - 1] == 0) {
                                definitelyDontUse[0] = checkForUse;
                                break;
                            }
                        }
                    } else {
                        while (definitelyDontUse[0] == 0) {
                            int counter = 0;
                            int checkForUse = 1 + counter;
                            guessNum++;
                            guess = checkForUse + " " + checkForUse + " " + checkForUse + " " + checkForUse;
                            getUserInput(scnr, guess, guessNum);
                            if (userNum1[guessNum - 1] + userNum2[guessNum - 1] == 0) {
                                definitelyDontUse[0] = checkForUse;
                                break;
                            }
                        }
                    }
                }else if (userNum1[1] + userNum2[1] == 0) {
                    int notThis = 3; //3,4,5,6 can't be used
                    if (definitelyDontUse[0] == 0) {
                        for (int x = 0; x < 4; x++) {
                            definitelyDontUse[x] = notThis;
                            notThis++;
                        }
                    }
                }
            } else if (definitelyDoUse[0] != 0) {
                System.out.println("definitely do use is not null");
                while ((userNum1[guessNum - 1] != 4) && (userNum2[guessNum - 1] != 4)) {
                    guessNum++;
                    createGuess(definitelyDontUse, definitelyDoUse, slot1, slot2, slot3, slot4, notSlot1, notSlot2, notSlot3, notSlot4, code);
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
                        if (code[0] != definitelyDontUse[0]) {
                            slot1 = code[0];
                        }
                        if (code[1] != definitelyDontUse[0]) {
                            slot2 = code[1];
                        }
                        if (code[2] != definitelyDontUse[0]) {
                            slot3 = code[2];
                        }
                        if (code[3] != definitelyDontUse[0]) {
                            slot4 = code[3];
                        }
                    }
                }
            }else{ //when there are no definitely do use, but there are definitely dont use

            }
System.out.println("Now ya done goofed");
        }
        //this might replace endGuesses, unknown until it is finished
        //createGuess(definitelyDontUse, definitelyDoUse, slot1, slot2, slot3, slot4, notSlot1, notSlot2, notSlot3, notSlot4, code);

        //not where this will be in code, these are basically final guesses.
        //endGuesses(definitelyDoUse, slot1, slot2, slot3, slot4, notSlot1, notSlot2, notSlot3, notSlot4, code);
    }

    public static void guessWithNoDo(int[] definitelyDontUse, int slot1, int slot2, int slot3, int slot4, int[] notSlot1, int[] notSlot2, int[] notSlot3, int[] notSlot4, int[] code){
        String guess;

    }

    public static void createGuess(int[] definitelyDontUse, int[] definitelyDoUse, int slot1, int slot2, int slot3, int slot4, int[] notSlot1, int[] notSlot2, int[] notSlot3, int[] notSlot4, int[] code) {
        String guess;
        boolean canUse = true;
        int x = 0;
        for (int counter = 0; counter > 3; counter++){
            code[counter] = 0;
        }
        while (code[0] == 0 || x < 4){
            switch (slot1){
                case 0:
                    for (int y = 0; y < 9; y++){
                        if (notSlot1[y] == definitelyDoUse[x]){
                            canUse = false;
                            break;
                        }
                    }
                    if (canUse){ //check to see if even need this if, because it doesn't seem necessary
                        code[0] = definitelyDoUse[x];
                        break;
                    }
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
                        for (int y = 0; y < 9; y++) {
                            if (notSlot2[y] == definitelyDoUse[x]) {
                                canUse = false;
                                break;
                            }
                        }
                        if (canUse) {
                            code[1] = definitelyDoUse[x];
                            break;
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
                            for (int y = 0; y<9; y++){
                                if (notSlot3[y] == definitelyDoUse[x]) {
                                    canUse = false;
                                    break;
                                }
                            }
                            if (canUse) {
                                code[2] = definitelyDoUse[x];
                                break;
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
                                for (int y = 0; y<9; y++){
                                    if (notSlot4[y] == definitelyDoUse[x]) {
                                        canUse = false;
                                        break;
                                    }
                                }
                                if (canUse) {
                                    code[3] = definitelyDoUse[x];
                                    break;
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
                    code[3] = definitelyDontUse[0];
                }
            } else{
                for (int loop = 2; loop <= 3; loop++) {
                    code[loop] = definitelyDontUse[0];
                }
            }
        } else{
            for (int loop = 1; loop <= 3; loop++) {
                code[loop] = definitelyDontUse[0];
            }
        }
        guess = code[0] + " " + code[1] + " " + code[2] + " " + code[3];
    }

    public void guessFromDont(int[] definitleyDontUse, int slot1, int slot2, int slot3, int slot4, int[] notSlot1, int[] notSlot2, int[] notSlot3, int[] notSlot4, int[] code) {
        String guess;
        boolean canUse = true;
        int x = 0;
        switch (slot1) {
            case 0:
                for (int x = 1; x <= 6; x++) {
                    for (int y = 0; y <= 5; y++) {
                        if ((definitleyDontUse[y] == x) || (notSlot1[y] == x)) {
                            canUse = false;
                        }
                }
                break;
            case 1:
                code[0] = 1;
                switch (slot2) {
                    case 0:
                        for (int counter = 0; counter < 6; counter ++){
                            if definitleyDontUse[counter] = counter{
                                canUse = false;
                            }
                        }
                    case 1:
                        code[1] = 1;
                        switch (slot3) {
                            case 0:
                                //FIX
                            case 1:
                                code[2] = 1;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 2:
                                code[2] = 2;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 3:
                                code[2] = 3;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 4:
                                code[2] = 4;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                        }
                        break;
                    case 2:
                        code[1] = 2;
                        switch (slot3) {
                            case 0:
                                //FIX
                            case 1:
                                code[2] = 1;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 2:
                                code[2] = 2;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 3:
                                code[2] = 3;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 4:
                                code[2] = 4;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                        }
                        break;
                    case 3:
                        code[1] = 3;
                        switch (slot3) {
                            case 0:
                                //FIX
                            case 1:
                                code[2] = 1;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 2:
                                code[2] = 2;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 3:
                                code[2] = 3;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 4:
                                code[2] = 4;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                        }
                        break;
                    case 4:
                        code[1] = 4;
                        switch (slot3) {
                            case 0:
                                //FIX
                            case 1:
                                code[2] = 1;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 2:
                                code[2] = 2;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 3:
                                code[2] = 3;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 4:
                                code[2] = 4;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                        }
                        break;
                }
                break;
            case 2:
                code[0] = 2;
                switch (slot2) {
                    case 0:
                        //FIX
                    case 1:
                        code[1] = 1;
                        switch (slot3) {
                            case 0:
                                //FIX
                            case 1:
                                code[2] = 1;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 2:
                                code[2] = 2;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 3:
                                code[2] = 3;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 4:
                                code[2] = 4;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                        }
                        break;
                    case 2:
                        code[1] = 2;
                        switch (slot3) {
                            case 0:
                                //FIX
                            case 1:
                                code[2] = 1;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 2:
                                code[2] = 2;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 3:
                                code[2] = 3;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 4:
                                code[2] = 4;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                        }
                        break;
                    case 3:
                        code[1] = 3;
                        switch (slot3) {
                            case 0:
                                //FIX
                            case 1:
                                code[2] = 1;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 2:
                                code[2] = 2;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 3:
                                code[2] = 3;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 4:
                                code[2] = 4;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                        }
                        break;
                    case 4:
                        code[1] = 4;
                        switch (slot3) {
                            case 0:
                                //FIX
                            case 1:
                                code[2] = 1;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 2:
                                code[2] = 2;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 3:
                                code[2] = 3;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 4:
                                code[2] = 4;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                        }
                        break;
                }
                break;
            case 3:
                code[0] = 3;
                switch (slot2) {
                    case 0:
                        //FIX
                    case 1:
                        code[1] = 1;
                        switch (slot3) {
                            case 0:
                                //FIX
                            case 1:
                                code[2] = 1;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 2:
                                code[2] = 2;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 3:
                                code[2] = 3;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 4:
                                code[2] = 4;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                        }
                        break;
                    case 2:
                        code[1] = 2;
                        switch (slot3) {
                            case 0:
                                //FIX
                            case 1:
                                code[2] = 1;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 2:
                                code[2] = 2;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 3:
                                code[2] = 3;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 4:
                                code[2] = 4;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                        }
                        break;
                    case 3:
                        code[1] = 3;
                        switch (slot3) {
                            case 0:
                                //FIX
                            case 1:
                                code[2] = 1;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 2:
                                code[2] = 2;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 3:
                                code[2] = 3;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 4:
                                code[2] = 4;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                        }
                        break;
                    case 4:
                        code[1] = 4;
                        switch (slot3) {
                            case 0:
                                //FIX
                            case 1:
                                code[2] = 1;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 2:
                                code[2] = 2;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 3:
                                code[2] = 3;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 4:
                                code[2] = 4;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                        }
                        break;
                }
                break;
            case 4:
                code[0] = 4;
                switch (slot2) {
                    case 0:
                        //FIX
                    case 1:
                        code[1] = 1;
                        switch (slot3) {
                            case 0:
                                //FIX
                            case 1:
                                code[2] = 1;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 2:
                                code[2] = 2;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 3:
                                code[2] = 3;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 4:
                                code[2] = 4;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                        }
                        break;
                    case 2:
                        code[1] = 2;
                        switch (slot3) {
                            case 0:
                                //FIX
                            case 1:
                                code[2] = 1;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 2:
                                code[2] = 2;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 3:
                                code[2] = 3;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 4:
                                code[2] = 4;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                        }
                        break;
                    case 3:
                        code[1] = 3;
                        switch (slot3) {
                            case 0:
                                //FIX
                            case 1:
                                code[2] = 1;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 2:
                                code[2] = 2;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 3:
                                code[2] = 3;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 4:
                                code[2] = 4;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                        }
                        break;
                    case 4:
                        code[1] = 4;
                        switch (slot3) {
                            case 0:
                                //FIX
                            case 1:
                                code[2] = 1;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 2:
                                code[2] = 2;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 3:
                                code[2] = 3;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                            case 4:
                                code[2] = 4;
                                switch (slot4) {
                                    case 0:
                                        //FIX
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
                                break;
                        }
                        break;
                }
                break;
        }
        guess = code[0] + " " + code[1] + " " + code[2] + " " + code[3];
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


