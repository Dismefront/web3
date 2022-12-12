const clock = document.getElementById("clock");
const date = document.getElementById("date");
function runClock() {
    const time = new Date();
    clock.innerHTML = time.toLocaleTimeString();
    date.innerHTML = time.toLocaleDateString();
}
runClock();
setInterval(runClock, 9000);