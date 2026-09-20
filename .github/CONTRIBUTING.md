## 🚀 The 4-Step Git Workflow

### 1. Create a Branch
Never write code directly to the `main` branch. Before typing a single line of code, create a new branch from `main` in Android Studio:
* **Naming standard:** Use lowercase with dashes. Describe what you are doing (e.g., `mechanum-tuning`, `blue-auto-park`, `claw-servo-fix`).

### 2. Write and Test Locally
* Write your OpModes or hardware classes.
* **Crucial:** Build the code and push it to the Control Hub via Wi-Fi or USB to test it on the actual robot. 
* Ensure your code does not break existing code or cause the robot to behave unsafely.

### 3. Commit and Push
* Open the Commit tab (`Ctrl + K` or `Cmd + K`).
* Write a clear, concise commit message.
  * *Good:* `Add slowing factor to driver 1 triggers`
  * *Bad:* `fixed code` or `stuff`
* Push your branch to GitHub (`Ctrl + Shift + K` or `Cmd + Shift + K`).

### 4. Open a Pull Request (PR)
* Go to our repository on GitHub and open a **Pull Request** from your branch to `main`.
* Tag at least one other programmer or mentor to review it.
* **Do not merge it yourself.** Wait for manual approval.

## 📝 Code Style Guidelines
To keep our code readable for everyone (and for judges!), please follow these rules:
* **Telemetry:** Keep telemetry clean. Clean up temporary or debugging telemetry before pushing your final code so the Driver Station screen doesn't lag.
* **Comments:** Document *why* you did something, especially for magic math numbers or PID tuning values (e.g., `// 0.45 threshold prevents motor burnout`).
* **Variables:** Use clear camelCase names (e.g., `armExtensionMotor` instead of `m1`).
