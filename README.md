# eventsChat
Плагин, который добавляет в майнкрафт ивенты от чата ютуб и чата твича! Работает с YouTubeStream и TwitchStream / Plugin that adds events from YouTube chat and Twitch chat to minecraft! Works with YouTubeStream and TwitchStream

# ENG
## Compile
1. Download the source code and upload it to Eclipse for example.
2. Add External JARs: [Server Core](https://getbukkit.org/download/craftbukkit) 1.12.2.
3. Add External JARs: [YouTubeStream](https://github.com/Dseym/streamInfoYouTube/releases/download/youtubeStream/youtubeStream.jar).
4. Add External JARs: [TwitchStream](https://github.com/Dseym/twitchStream/releases/download/twitchStream/twitchStream.jar).
5. Install [lombok](https://projectlombok.org/).
6. Now you have the code that you can edit!

## Install for Server
1. Download the compiled [EventsChat](https://github.com/Dseym/eventsChat/releases/download/eventsChat/eventsChat.jar) and upload it to your server.
2. Download the compiled [YouTubeStream](https://github.com/Dseym/streamInfoYouTube/releases/download/youtubeStream/youtubeStream.jar) and upload it to your server.
3. Download the compiled [TwitchStream](https://github.com/Dseym/twitchStream/releases/download/twitchStream/twitchStream.jar) and upload it to your server.
4. Set up config.
5. Have fun!

## Commands
1. /events youtube set <videoID> - set ID stream, watch?v=ID (MVXja46rOOg)
2. /events twitch set <channelName> - set channelName
3. /events stop - disconnect

## Config
```yml
twitch:
  oauth: "*yourOAUTH*"
  nick: "*yourNick*"

youtube:
  api: "yourAPI"
```

## GameEvents
Now the plugin already has 25 pre-installed events.
```
List - event:
 -heart, +heart, hardcore, hello
 anvil, poison, lava, drop, apple
 tp, dragon, wither, item, notch
 bomb, creeper, error, eat, totem
 grass, hole, iron, pickaxe
 obsidian, pumpkin, zeus

```
Example in chat: !event event

## Bettings
There is only one bet in the game now.
```
List - bet:
 died

```
Example in chat: !bet **nameActiveBet** **yes/no** **money**

## API
Register custom GameEvent
```java
constructor GameEvent(String name /*how will be called from the chat*/, String message /*what message the targets will receive*/, String cost /*cost event*/);
GameEventManager.getManager().registerEvent(new <? extends GameEvent>);
```
New custom betting
```java
constructor Betting(String name /*how will be called from the chat*/, String description /*description*/, Plugin plugin, int time /*duration of the bet*/);
BettingManager.getManager().newBetting(new <? extends Betting>);
```
Events:
```java
CallGameEventEvent //called when GameEvent is called
ChatEvent //called when a new message appears
NewBettingEvent //called when a new bid is created
NewSpectatorEvent //called when a new viewer appears in the chat
```

Soon more!

# RUS
## Компиляция
1. Скачайте исходный код и загрузите, к примеру, в Eclipse.
2. Добавьте External Jars в проект: [Серверное ядро](https://getbukkit.org/download/craftbukkit) 1.12.2.
3. Добавьте External Jars в проект: [YouTubeStream](https://github.com/Dseym/streamInfoYouTube/releases/download/youtubeStream/youtubeStream.jar).
4. Добавьте External Jars в проект: [TwitchStream](https://github.com/Dseym/twitchStream/releases/download/twitchStream/twitchStream.jar).
5. Установите [lombok](https://projectlombok.org/).
6. Теперь у Вас есть код для редактирования!

## Установка на сервер
1. Скачайте скомпилированный [EventsChat](https://github.com/Dseym/eventsChat/releases/download/eventsChat/eventsChat.jar) и загрузите на свой сервер.
2. Скачайте скомпилированный [YouTubeStream](https://github.com/Dseym/streamInfoYouTube/releases/download/youtubeStream/youtubeStream.jar) и загрузите на свой сервер.
3. Скачайте скомпилированный [TwitchStream](https://github.com/Dseym/twitchStream/releases/download/twitchStream/twitchStream.jar) и загрузите на свой сервер.
4. Настройте конфиг.
5. Веселитесь!

## Commands
1. /events youtube set <videoID> - установить ID стрима, watch?v=ID (MVXja46rOOg)
2. /events twitch set <channelName> - установить channelName
3. /events stop - отключиться

## Конфиг
```yml
twitch:
  oauth: "*yourOAUTH*"
  nick: "*yourNick*"

youtube:
  api: "yourAPI"
```

## GameEvents
Сейчас в плагине 25 предустановленных ивентов.
```
Спиоск - event:
 -heart, +heart, hardcore, hello
 anvil, poison, lava, drop, apple
 tp, dragon, wither, item, notch
 bomb, creeper, error, eat, totem
 grass, hole, iron, pickaxe
 obsidian, pumpkin, zeus

```
Пример в чате: !event event

## Bettings
Сейчас в плагине только 1 ставка.
```
Список - bet:
 died

```
Пример в чате: !bet **activeBet** **yes/no** **money**

## API
Регистрация кастомного GameEvent
```java
constructor GameEvent(String name /*как будет вызыватся из чата*/, String message /*какое сообщение будет отправляться цели*/, String cost /*стоимость ивента*/);
GameEventManager.getManager().registerEvent(new <? extends GameEvent>);
```
New custom betting
```java
constructor Betting(String name /*как будет вызыватся из чата*/, String description /*описание*/, Plugin plugin, int time /*длительность ставки*/);
BettingManager.getManager().newBetting(new <? extends Betting>);
```
Events:
```java
CallGameEventEvent //вызывается когда вызывается GameEvent
ChatEvent //вызывается когда появляется новое сообщение
NewBettingEvent //вызывается когда создается новая ставка
NewSpectatorEvent //вызывается когда появляется новый зритель в чате
```

Скоро больше!

Для свободного использование.