# 📱 Den Internship Week 4 – Motivational Quotes & Panda App

This project was developed as **Internship Task 4**, focusing on **AdMob monetization**. 
The app has a **motivational theme** — designed to brighten the user's day and add a smile with a surprise panda moment 🐼.

---

## 🎯 Objective
Implement **AdMob ads** (Open Ad, Banner Ad, Interstitial Ad) , while building a 5-screen Android app. The UI is clean, user-friendly, and policy-compliant with Google’s ad guidelines.

---

## 🌟 App Theme & Flow

1. **Splash Screen**  
   - Simple and welcoming splash screen.  
   - After splash, an **Open Ad** is displayed (following Google Play guidelines).

2. **Register & Login Screens** *(SharedPreferences for storage)*  
   - Clean, intuitive UI for registration and login.  
   - User data stored locally using SharedPreferences.

3. **Mood Selection Screen** – *"How is your day going?"*  
   - User chooses their current mood.  
   - Based on the mood, the app fetches and displays a **motivational quote** to brighten their day.

4. **Panda Offer Screen** – *"Want to see a panda?"* 🐼  
   - Everyone loves pandas! The app offers to show the user a cute panda image.  
   - **Interstitial Ad** is displayed here **before** moving to the panda screen (only if loaded; otherwise, skip ad).

5. **Panda Screen** – *Happiness overload!*  
   - Shows a **random cute panda image**.  
   - Banner ad is displayed at the bottom.  
   - User can choose to close the app from here.

---

## 📢 Ad Implementations

- **Open Ad** → shown right after splash screen.  
- **Banner Ad** → shown on the bottom of every screen (except splash).  
- **Interstitial Ad** → shown after the 3rd screen, before opening the panda screen.
- AdMob test IDs used for development.  
- All ads comply with **Google AdMob & Play Store policies**.

---

## 🛠 Technical Details

- **Language:** Java  
- **UI:** XML Layouts  
- **Ad Platform:** Google AdMob
-  **Local Storage:** SharedPreferences (for user login data)  
- **Internet Handling:** Proper checks for slow/no internet  
- **Error Handling:** No crashes; app gracefully continues if ad not loaded

__________________________________________________________
__________________________________________________________
  
