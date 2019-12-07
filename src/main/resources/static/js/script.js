function functionChange(){
    console.log('amar', this);
}


var radios = document.querySelectorAll('input[type=radio][name="typeOperation"]');

console.log(radios)
function changeHandler(event) {
if(this.value === 'virement'){
    document.querySelector('.compte_to').classList.remove('displayNone')
}else{
    document.querySelector('.compte_to').classList.add('displayNone')
}
}

Array.prototype.forEach.call(radios, function(radio) {
   radio.addEventListener('change', changeHandler);
});