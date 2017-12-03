
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
       public static void main(String[] args) {
           ApiContextInitializer.init(); // Инициализируем апи
           TelegramBotsApi botapi = new TelegramBotsApi();

           try {
               botapi.registerBot(new Bot());
           } catch (TelegramApiException e) {
               e.printStackTrace();
           }
       }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            if (message.getText().equals("/help")) {
                sendMsg(message,"Проверка на /help выполнена");
            } else if (message.getText().equals("привет") || message.getText().equals("hello")) {
                sendMsg(message,"Привет пользователь!");
            } else if (message.getText().equals("что умеешь делать")) {
                sendMsg(message,"Приветствовать и прощаться");
            } else if (message.getText().equals("пока")) {
                sendMsg(message, "Пока пользователь!");
            } else if (message.getText().equals("как дела")) {
                sendMsg(message, "Все прекрасно!   Как у вас?");
                if (message.getText().equals("хорошо") || message.getText().equals("нормально") || message.getText().equals("отлично")) {
                    sendMsg(message, "Я рад за вас!");
                } else {
                    sendMsg(message, "Я могу помочь?");
                    if (message.getText().equals("да")) {
                        sendMsg(message, "Чем?");
                        help();
                    }
                }
            }
        }
    }
    private void sendMsg(Message message, String s ) {
           SendMessage sendMessage = new SendMessage();
           sendMessage.enableMarkdown(true);
           sendMessage.setChatId(message.getChatId().toString());
           sendMessage.setReplyToMessageId(message.getMessageId());
           sendMessage.setText(s);
           try {
               sendMessage(sendMessage);
           } catch (TelegramApiException e) {
               e.printStackTrace();
           }
    }
    private void help() {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Действие (1)");
        sendMessage.setText("Действие (2)");
        sendMessage.setText("Действие (3)");
    }

       @Override
       public String getBotUsername() {
           return "SobolRosBot";
           //возвращаем юзера
       }

       @Override
       public String getBotToken() {
           return "382237573:AAHJi3TAiv9ikPcmZh9qb2EddRuND2WKuxM";
           //Токен бота
       }
}
