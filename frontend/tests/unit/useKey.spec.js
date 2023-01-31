import {mount} from '@vue/test-utils'
import useKey from "@/composables/useKey";

describe('useKey unit tests', () => {

  it('initial value is 0', () => {
    expect(useKey().keyValue.value).toBe(0)
  })

  it('incremented value gives 1 keyValue', () => {
    let key = useKey(0,1);
    key.incrementKey();
    expect(key.keyValue.value).toBe(1)
  })

  it('useKey with initialCount 1 and stepsize 2 gives 3 after incrementKey', () => {
    let key = useKey(1,2);
    key.incrementKey();
    expect(key.keyValue.value).toBe(3)
  })
})
