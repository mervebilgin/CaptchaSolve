# 🛠️ CaptchaSolve - Solving CAPTCHAs with Java and Tesseract OCR

CaptchaSolve is a **Java-based application** that processes and solves CAPTCHA images using **Tesseract OCR**.  
It applies **various image processing techniques** such as **color transformation, rotation, and contrast adjustment** to enhance OCR accuracy.  

📸 **Extract text from CAPTCHA images**  
🎨 **Apply preprocessing techniques for better recognition**  
🔍 **Automate solving attempts with multiple retries**  

---

## 🚀 Features

✔️ **Tesseract OCR integration** for text extraction  
✔️ **Image processing** (color filtering, rotation, and brightness adjustment)  
✔️ **Handles multiple CAPTCHA images**  
✔️ **Automated solving attempts with error handling**  
✔️ **Maven-based dependency management**  

---

## 🏗️ Project Structure

<img src="https://github.com/user-attachments/assets/121c05b8-6805-4ced-9f07-b7766bddd943" alt="captchaSolve" width="400" height="370"/>

---

## 📦 Installation & Setup

### **🔹 Prerequisites**
- **Java 17** or higher  
- **Maven** (for dependency management)  
- **Tesseract OCR** installed (or use the bundled `tess4j` dependency)

### **🔹 Clone the Repository**
```bash
git clone https://github.com/your-username/CaptchaSolve.git
cd CaptchaSolve
```

### **🔹 Install Dependencies**
```bash
mvn clean install
```

---
## ⚡ How to Run

To execute the program, run:
```bash
CaptchaSolve\src\test\java\TesseractExample
```
- ● The program will **process CAPTCHA images** stored in `src/test/java/image/`
- ● It will **apply image transformations** (color filtering, rotation, and contrast adjustment) and extract text using **Tesseract OCR**
- ● The **processed images** will be saved in `src/test/java/convertImage/`
- ● Results will be **printed in the console** for verification

---

## 🛠️ Configuration

Modify the Tesseract data path in **TesseractExample.java**:
```bash
instance.setDatapath("src/test/java/data");
```

---

## ✅ Usage Example

Example console output for a CAPTCHA image:
```bash
Attempt 1 for image 1 --> |EDka
Incorrect value, retrying...
Attempt 2 for image 1 --> |CDKa o
Incorrect value, retrying...
Attempt 3 for image 1 --> Cikg .
o 1d
Incorrect value, retrying...
Attempt 4 for image 1 --> " ST
o Ra
Incorrect value, retrying...
Attempt 5 for image 1 --> IEDka
Correct value for image 1: IEDka
```

---

### 📌 Dependencies

This project uses the following dependencies:

 ● **[Tess4J (Tesseract OCR)](https://github.com/nguyenq/tess4j)** for text extraction  
 ● **Maven** for dependency management  

Dependencies are **automatically installed via Maven**.
