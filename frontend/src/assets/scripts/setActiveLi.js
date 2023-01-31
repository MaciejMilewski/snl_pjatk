export default function activeLi(index, ulElement){
    let curr = 0;
    ulElement.forEach((li)=>{
        if(curr == index){
            li.className = 'uk-active';
        }else{
            li.className = '';
        }
        curr++
    })
}