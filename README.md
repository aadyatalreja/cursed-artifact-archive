# Cursed Artifacts Archive 

Cursed Artifacts Archive is a Java-based desktop application that allows users to manage a collection of fictional cursed artifacts. Users can add, search, and view detailed information about various artifacts, all wrapped in an aesthetically rich parchment-styled interface. This project showcases object-oriented design, Swing-based GUI development, and creative UI design in Java.

---

## Features

- **Artifact Management**: Add cursed artifacts with details such as name, type, origin, curse, and powers.
- **Search Functionality**: Search the archive using keywords across all artifact fields.
- **Aesthetic UI**: A parchment-inspired UI with styled text areas, buttons, and scroll panes.
- **Data Visualization**: View a complete list of artifacts with formatted descriptions.
- **Custom Font & Colors**: Enhanced readability with custom fonts and thematic colors.

---

## Technologies Used

- **Java (JDK 8 or higher)**: Core programming language.
- **Swing**: For building the graphical user interface (GUI).
- **Java AWT**: For rendering custom graphics like the parchment background.

---

## Usage

1. **Adding Artifacts**:
   - Enter artifact details in the input fields.
   - Click on the `⚔️ Add to Collection ⚔️` button.
   - The artifact will be added to the archive, and its details will appear in the output area.

2. **Searching Artifacts**:
   - Click on the `🔍 Search the Archives 🔍` button.
   - Enter a search term in the dialog box.
   - View matching artifacts in the output area.

3. **Viewing All Artifacts**:
   - The entire collection of artifacts is displayed in the output area upon startup or after adding an artifact.

---

## Code Structure

- **`Artifact`** (Abstract Class): Represents the blueprint of an artifact.
  - Includes fields for name, type, origin, curse, and powers.
  - Abstract method: `getArtifactDetails()`.
  
- **`CursedArtifact`** (Concrete Class): Implements `Artifact`, adding specific details and behavior.

- **`ArtifactManager`**: Manages the list of artifacts and provides methods for adding, searching, and retrieving artifacts.

- **`ParchmentPanel`**: Custom JPanel for rendering a parchment-styled background.

- **`CursedArtifactsArchive`** (Main Class): Handles the GUI setup, event handling, and application logic.

---
