import {ref} from "vue";

export default function useKey(initialCount = 0, stepSize = 1 ){
    const keyValue = ref(initialCount)

    function incrementKey(){
        keyValue.value+=stepSize;
    }
    return {
        keyValue,
        incrementKey
    }
}