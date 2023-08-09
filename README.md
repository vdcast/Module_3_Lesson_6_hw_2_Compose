# Module_3_Lesson_6_hw_2_Compose

**Lesson's topic:**

Architecture. MVVM.

**Task:**

Create a conditionally "empty" program on the MVP architecture, no special design. As a server, we take any API (you can choose any API) or connect to any site.
Let's the application name be "Smart House Manager".
1) Main screen: "Light", "Kitchen", "Case", "Air conditioning", "Settings".
2) "Light": It has screens of "Light Control" (it has a bunch of on / off switches, which are connected to the server by clicking, the answer is 200 - ok), "Statistics" (with a number consumption for this month, for the last and for the year). Several TextFields to make adjustments manually. Data need to be stored somewhere.
3) "Kitchen": Here is one screen with a button - "Start cooking". Cooking time - 1 min.
Logic: when the button is pressed, the cooking is started with a countdown, send to DB the current time (millis). Then think about how to implement the logic if entered the screen during cooking or the end field.
4) "Tasks" - a scrolling list of tasks, there is a button for adding a case. And there is a button to clear the to-do list. When pressed, it immediately throws it into the to-do list. The to-do list is stored in a database.
5) "Air conditioning" - Description with the current temperature and "increase" and "decrease" temperature buttons.
6) "Settings" - It has a menu: "Advanced settings", "Memory", "About".
In “Advanced settings” you can change cooking time and step of changing temperature of air conditioner.
In the "Memory" you can set maximum number of tasks.

**Result: Screen recoding** (please, note that it was converted to GIF, so it's not smooth as it was):

![Module_3_Lesson_6_hw_2_Compose](https://github.com/vdcast/Module_3_Lesson_6_hw_2_Compose/assets/108469609/1e3740d9-a430-4c83-af6c-241fbf60286b)
