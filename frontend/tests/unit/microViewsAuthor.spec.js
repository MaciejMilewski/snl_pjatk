import {mount} from "@vue/test-utils";
import Author from "@/views/Inputs/Author";
describe('MicroViews unit tests', () => {

    it(' is visible ', () => {
        const wrapper = mount(Author);
        expect(wrapper.isVisible()).toBeTruthy()
    })

    it(' is html <input type="text" placeholder="Autor" class="uk-input" id="autor"> ', () => {
        const wrapper = mount(Author);
        expect(wrapper.html()).toBe("<input type=\"text\" placeholder=\"Autor\" class=\"uk-input\" id=\"autor\">")
    })

    it(' Author with propData author: Jan Kowalski is Jan Kowalski ', () => {
        const wrapper = mount(Author, {
            propsData: {
                author:"Jan Kowalski"
            }
        });
        expect(wrapper.element._value).toBe("Jan Kowalski")
    })

    it(' name is AuthorView ', () => {
        const wrapper = mount(Author);
        expect(wrapper.getCurrentComponent().vnode.type.name).toBe('AuthorView')
    })

    it(' is of type `text` ', () => {
        const wrapper = mount(Author);
        expect(wrapper.getCurrentComponent().subTree.props.type).toBe('text')
    })

    it(' has id `autor` ', () => {
        const wrapper = mount(Author);
        expect(wrapper.getCurrentComponent().subTree.props.id).toBe('autor')
    })

    it(' has placeholder `Autor` ', () => {
        const wrapper = mount(Author);
        expect(wrapper.getCurrentComponent().subTree.props.placeholder).toBe('Autor')
    })

    it(' has no components ', () => {
        const wrapper = mount(Author);
        //[object Object] when empty
        expect(wrapper.getCurrentComponent().type.components.toString()).toBe('[object Object]')

    })


})
