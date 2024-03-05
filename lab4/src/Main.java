import classes.Story;
import exceptions.WrongStoryLogicException;

// Однако на этой бутылочке не было ни черепа, ни костей, ни надписи "Яд!", и
// Алиса рискнула попробовать ее содержимое. А так как оно оказалось необыкновенно
// вкусным (на вкус - точь-в-точь смесь вишневого пирога, омлета, ананаса,
// жареной индюшки, тянучки и горячих гренков с маслом), она сама не заметила, как пузырек опустел.
//— Ой, что же это со мной делается! — сказала Алиса.
// — Я, наверное, и правда складываюсь, как подзорная труба!


public class Main {
    public static void main(String[] args) {
        try {
            Story.storyTelling();
        } catch (WrongStoryLogicException e) {
            throw new RuntimeException(e);
        }
    }
}