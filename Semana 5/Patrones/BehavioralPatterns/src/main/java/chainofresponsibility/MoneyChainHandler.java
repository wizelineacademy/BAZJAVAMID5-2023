package chainofresponsibility;

public abstract class MoneyChainHandler {
    MoneyChainHandler nextHandler = null;
    protected Integer noteDenomination = 0;

    public MoneyChainHandler setNextHandler (MoneyChainHandler nextHandler) {
        this.nextHandler = nextHandler;
        return this.nextHandler;
    }

    public void handler (int dollarBill) {
        int notes = dollarBill / noteDenomination;
        int remainingAmount = dollarBill % noteDenomination;

        if (notes > 0) {
            System.out.printf("dispatched %d X %d = %d handled by %s \n",
                    noteDenomination, notes, (noteDenomination * notes), this.getClass().getSimpleName());
        }

        if (nextHandler != null && remainingAmount > 0) {
            nextHandler.handler(remainingAmount);
        }
    }

}

class HundrenDollarHandler_100 extends MoneyChainHandler {
    public HundrenDollarHandler_100 () {
        this.noteDenomination = 100;
    }
}

class FiftyDollarHandler_50 extends MoneyChainHandler {
    public FiftyDollarHandler_50 () {
        this.noteDenomination = 50;
    }
}

class TenDollarHandler_10 extends MoneyChainHandler {
    public TenDollarHandler_10 () {
        this.noteDenomination = 10;
    }
}

class FiveDollarHandler_5 extends MoneyChainHandler {
    public FiveDollarHandler_5 () {
        this.noteDenomination = 5;
    }
}

class TwoDollarHandler_2 extends MoneyChainHandler {
    public TwoDollarHandler_2 () {
        this.noteDenomination = 2;
    }
}

class OneDollarHandler_1 extends MoneyChainHandler {
    public OneDollarHandler_1 () {
        this.noteDenomination = 1;
    }
}
