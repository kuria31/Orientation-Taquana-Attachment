document.addEventListener('DOMContentLoaded', () => {
    const savedSection = localStorage.getItem('activeSection') || 'main-content';
    toggleSections(savedSection); // Default to 'main-content' if no saved section is found
});

function toggleSections(sectionToShow) {
    const allSections = ['issueTicket', 'registerVehicle', 'travellingVehicles', 'vehiclesInQueue', 'main-content'];

    // Hide all sections first
    allSections.forEach(id => {
        const section = document.getElementById(id);
        if (section) {
            section.classList.remove('fade-in');
            section.classList.add('fade-out');
            section.style.display = (id === sectionToShow) ? 'block' : 'none';
        }
    });

    // Add fade-in class to the selected section
    const selectedSection = document.getElementById(sectionToShow);
    if (selectedSection) {
        selectedSection.classList.remove('fade-out');
        selectedSection.classList.add('fade-in');
    }

    // Save the current active section to localStorage
    localStorage.setItem('activeSection', sectionToShow);

    // Highlight the active tab
    highlightActiveTab(sectionToShow);
}

function highlightActiveTab(sectionToShow) {
    const buttons = document.querySelectorAll('.btn-link');
    buttons.forEach(button => {
        button.classList.remove('active');
    });

    const activeButton = document.getElementById(sectionToShow + 'Link');
    if (activeButton) {
        activeButton.classList.add('active');
    }
}


function togglePassengers(button) {
    // Get the passenger list div
    const passengerList = button.nextElementSibling;

    // Toggle visibility of the passenger list
    if (passengerList.style.display === "none") {
        passengerList.style.display = "block";
        button.textContent = "See Less"; // Update button text
    } else {
        passengerList.style.display = "none";
        button.textContent = "See More"; // Update button text
    }
}