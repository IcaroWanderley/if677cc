#include <curses.h>

int main(void)
{
    initscr();

    if (has_colors() == FALSE)
    {
        printw("Seu terminal não suporta cores!\n");
        refresh();
        getch();
        endwin();
        return 1;
    }

    start_color();

    init_pair(1, COLOR_BLACK, COLOR_CYAN);
    init_pair(2, COLOR_WHITE, COLOR_RED);

    attron(COLOR_PAIR(1));
    printw("Isso deve ser impresso em preto em um fundo ciano.\n");
    attron(COLOR_PAIR(2));
    printw("Isso deve ser impresso em branco em um fundo vermelho.\n");
    refresh();

    getch();
    endwin();
    return 0;
}


