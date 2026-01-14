let currentStep = 0;
const steps = document.querySelectorAll(".step");
const sections = document.querySelectorAll(".section");

const backBtn = document.querySelector(".previous");
const nextBtn = document.querySelector(".nexts");

function updateUI() {
	steps.forEach((step, i) =>
		step.classList.toggle("active", i === currentStep)
	);
	sections.forEach((section, i) =>
		section.classList.toggle("active", i === currentStep)
	);

	/* ---- Button Logic ---- */

	// Step 1: hide Back button
	if (currentStep === 0) {
		backBtn.style.display = "none";
		nextBtn.textContent = "Next";
	} 
	// Step 2: show Back, change Next to Submit
	else if (currentStep === 1) {
		backBtn.style.display = "inline-block";
		nextBtn.textContent = "Submit";
	} 
	// Other steps
	else if(currentStep ===2){
		backBtn.style.display = "none";
		nextBtn.style.display = "none";
	}else{
		backBtn.style.display = "inline-block";
		nextBtn.textContent = "Next";
	}
}

function nextStep() {
	if (currentStep < sections.length - 1) {
		currentStep++;
		updateUI();
	}
}

function prevStep() {
	if (currentStep > 0) {
		currentStep--;
		updateUI();
	}
}

// Initialize on load
updateUI();


/* ===============================
   PACKAGE SELECTION
================================ */
function selectPackage(element, packageId, packageName) {
    // activate selected card
    document.querySelectorAll(".package-card").forEach(card => card.classList.remove("active"));
    element.classList.add("active");

    // set packageId for form submission
    document.getElementById("packageId").value = packageId;

    // show packageName in confirmation step
    document.getElementById("confirmPackage").innerText = packageName;
}





/* ------------------------------
   CALENDAR FOR UL / LI STRUCTURE
--------------------------------*/
const currentMonth = document.querySelector(".current-month");
const calendarDates = document.querySelector(".calendar-dates");
const monthBtns = document.querySelectorAll(".month-btn");

let today = new Date();
today.setHours(0,0,0,0);

let date = new Date();

/* Show current month */
currentMonth.textContent = date.toLocaleDateString("en-US", {
    month: "long",
    year: "numeric"
});

/* Render calendar on load */
renderCalendar();

function renderCalendar() {
    const year = date.getFullYear();
    const month = date.getMonth();

    const prevLastDay = new Date(year, month, 0).getDate();
    const totalMonthDay = new Date(year, month + 1, 0).getDate();
    const startWeekDay = new Date(year, month, 1).getDay();

    calendarDates.innerHTML = "";

    for (let i = 0; i < 42; i++) {
        let day = i - startWeekDay + 1;
        const li = document.createElement("li");

        if(day <= 0){
            li.textContent = prevLastDay + day;
            li.classList.add("padding-day");
        } else if(day > totalMonthDay){
            li.textContent = day - totalMonthDay;
            li.classList.add("padding-day");
        } else {
            li.textContent = day;
            if(day === today.getDate() && month === today.getMonth() && year === today.getFullYear()){
                li.classList.add("current-day");
            } else {
                li.classList.add("month-day");
            }
        }

        calendarDates.appendChild(li);
    }
}

/* MONTH NAVIGATION */
monthBtns.forEach(btn => {
    btn.addEventListener("click", () => {
        if(btn.classList.contains("prev")){
            date.setMonth(date.getMonth() - 1);
        } else {
            date.setMonth(date.getMonth() + 1);
        }

        currentMonth.textContent = date.toLocaleDateString("en-US", {
            month: "long",
            year: "numeric"
        });

        renderCalendar();
    });
});
