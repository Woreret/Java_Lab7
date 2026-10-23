public final class SendGridSecurityNotifier implements SecurityNotifier {

    @Override
    public void sendSecurityAlert(String email, String message) {
        System.out.println("[SendGrid] лист на " + email + ": " + message);
    }
}

// справжня реалізація сповіщень (поки просто друкує текст)
