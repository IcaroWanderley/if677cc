#include <ncurses.h>

int main()
{
    initscr();

    /* Com o cbreak(), assim que uma tecla for pressionada, ela será passada ao programa.
     * No GNOME Terminal, essa função já é habilitada por padrão */
    cbreak();

    /* Usando noecho(), garantimos que os inputs não serão impressos de volta no terminal.
     * No GNOME Terminal, essa opção também já é habilitada por padrão */
    noecho();

    keypad(stdscr, TRUE);

    printw("Pressione uma tecla...\n");
    int ch = getch(); // codigo ASCII
    switch (ch)
    {
    case KEY_BACKSPACE:
        printw("Você pressionou a tecla BACKSPACE!\n");
        break;
    case KEY_DC:
        printw("Você pressionou a tecla DELETE!\n");
        break;
    default:
        printw("Não reconheço essa tecla!\n");
        break;
    }

    refresh();

    getch();
    endwin();
    return 0;
}


